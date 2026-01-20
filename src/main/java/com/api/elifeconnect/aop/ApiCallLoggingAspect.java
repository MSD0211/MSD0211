package com.api.elifeconnect.aop;

import com.api.elifeconnect.entity.ApiCallLog;
import com.api.elifeconnect.repository.ApiCallLogRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ApiCallLoggingAspect {

    private final ObjectMapper mapper;
    private final HttpServletRequest request;
    private final ApiCallLogRepository repo;

    @Around("@annotation(logApiCall)")
    public Object logApiCall(ProceedingJoinPoint pjp,
                             LogApiCall logApiCall) throws Throwable {

        Instant startTime = Instant.now();

        String apiName = logApiCall.value();
        String httpMethod = request.getMethod();
        String url = request.getRequestURI();
        String clientId = extractClientIdFromJwt();

        // ⭐ NEW: Extract client IP
        String clientIp = getClientIp();

        String requestHeaders = safe(this::extractHeaders);
        String requestPayload = safe(() -> extractRequestPayload(pjp.getArgs()));
        String referenceId = safe(() -> extractReferenceIdDeep(requestPayload));

        ApiCallLog logEntry = ApiCallLog.builder()
                .apiName(apiName)
                .httpMethod(httpMethod)
                .url(url)
                .clientId(clientId)
                .clientIp(clientIp)  // ⭐ NEW FIELD
                .referenceId(referenceId)
                .requestHeaders(requestHeaders)
                .requestPayload(requestPayload)
                .startTime(startTime)
                .createdAt(Instant.now())
                .build();

        Object result;

        try {
            result = pjp.proceed();

            String responseBody = safe(() -> {
                try {
                    return mapper.writeValueAsString(result);
                } catch (Exception ex) {
                    return null;
                }
            });

            logEntry.setResponsePayload(responseBody);
            logEntry.setSuccess(true);
            logEntry.setHttpStatus(200);

        } catch (Exception ex) {

            logEntry.setSuccess(false);
            logEntry.setErrorMessage(ex.getMessage());
            logEntry.setHttpStatus(500);
            throw ex;

        } finally {

            Instant end = Instant.now();
            logEntry.setEndTime(end);
            logEntry.setDurationMs(Duration.between(startTime, end).toMillis());

            repo.save(logEntry);
        }

        return result;
    }

    // ===================== UTILITIES =========================

    private String extractClientIdFromJwt() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
                return jwt.getClaimAsString("azp");
            }
        } catch (Exception ignored) { }
        return null;
    }

    // ⭐ NEW — Extract client IP safely (supports proxies)
    private String getClientIp() {
    String[] HEADERS = {
            "X-Forwarded-For",
            "X-Real-IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    for (String header : HEADERS) {
        String ip = request.getHeader(header);
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            ip = ip.split(",")[0].trim();
            return normalizeIp(ip);
        }
    }

    return normalizeIp(request.getRemoteAddr());
}

private String normalizeIp(String ip) {
    if (ip == null) return null;

    // Convert IPv6 localhost → IPv4
    if ("0:0:0:0:0:0:0:1".equals(ip) || "::1".equals(ip)) {
        return "127.0.0.1";
    }

    return ip;
}


    private String extractHeaders() {
        try {
            Map<String, String> map = new HashMap<>();
            Enumeration<String> headerNames = request.getHeaderNames();

            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                map.put(name, request.getHeader(name));
            }
            return mapper.writeValueAsString(map);

        } catch (Exception e) {
            return null;
        }
    }

    private String extractRequestPayload(Object[] args) {
        try {
            for (Object arg : args) {
                if (arg != null && !(arg instanceof HttpServletRequest)) {
                    return mapper.writeValueAsString(arg);
                }
            }
        } catch (Exception ignored) { }
        return null;
    }

    private String extractReferenceIdDeep(String json) {
        if (json == null) return null;

        try {
            JsonNode node = mapper.readTree(json);
            return findReference(node);
        } catch (Exception e) {
            return null;
        }
    }

    private String findReference(JsonNode node) {
        if (node == null) return null;

        if (node.has("referenceId")) return node.get("referenceId").asText();
        if (node.has("referenceNo")) return node.get("referenceNo").asText();
        if (node.has("referenceNumber")) return node.get("referenceNumber").asText();
        if (node.has("id")) return node.get("id").asText();

        for (JsonNode child : node) {
            String ref = findReference(child);
            if (ref != null) return ref;
        }
        return null;
    }

    private String safe(SupplierWithException<String> fn) {
        try {
            return fn.get();
        } catch (Exception e) {
            return null;
        }
    }

    @FunctionalInterface
    interface SupplierWithException<T> {
        T get() throws Exception;
    }
}

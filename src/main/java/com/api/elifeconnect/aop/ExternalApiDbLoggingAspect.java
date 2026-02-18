package com.api.elifeconnect.aop;

import com.api.elifeconnect.entity.ExternalApiLog;
import com.api.elifeconnect.repository.ExternalApiLogRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ExternalApiDbLoggingAspect {

    private final ExternalApiLogRepository repo;
    private final ObjectMapper mapper = new ObjectMapper();

    @Around("@annotation(logExternalCall)")
    public Object logExternalApi(ProceedingJoinPoint joinPoint,
                                 LogExternalCall logExternalCall) throws Throwable {

        LocalDateTime startTime = LocalDateTime.now();

        // String apiName = logExternalCall.value();
        String apiNameTmp = MDC.get("apiName");
        String apiName = (apiNameTmp != null) ? apiNameTmp : "UnknownAPI";

        Object[] args = joinPoint.getArgs();

        String url = extractUrl(args);
        String requestBodyJson = extractPayload(args);
        log.info("REQUEST BODY JSON::"+requestBodyJson);
        String requestHeadersJson = extractHeaders(args);

        // Extract referenceId (PK)
        String referenceId = extractReferenceId(requestBodyJson);
        log.info("REFERENCE ID::"+referenceId);
        if (referenceId == null) {
            log.error("❌ Missing referenceId in request body. Cannot log API call.");
        }

        // Insert initial log
        ExternalApiLog logEntry = ExternalApiLog.builder()
                .referenceId(referenceId)
                .apiName(apiName)
                .httpMethod("POST")
                .url(url)
                .requestPayload(requestBodyJson)
                .requestHeaders(requestHeadersJson)
                .startTime(startTime)
                .createdAt(LocalDateTime.now())
                .build();

        repo.save(logEntry);

        log.info("➡️ [{}] API Call Started | RefID={} | URL={}", apiName, referenceId, url);

        try {
            Object result = joinPoint.proceed();

            if (result instanceof Mono<?> mono) {
                return mono
                        .doOnSuccess(res -> handleSuccess(res, referenceId, apiName, startTime))
                        .doOnError(ex -> handleFailure(ex, referenceId, apiName, startTime));
            }

            handleSuccess(result, referenceId, apiName, startTime);
            return result;

        } catch (Exception ex) {
            handleFailure(ex, referenceId, apiName, startTime);
            throw ex;
        }
    }

    private void handleSuccess(Object result, String referenceId, String apiName, LocalDateTime startTime) {
        repo.findById(referenceId).ifPresent(logEntry -> {
            logEntry.setResponsePayload(toJson(result));
            logEntry.setResponseHeaders("{}");
            logEntry.setHttpStatus(200);
            logEntry.setSuccess(true);
            logEntry.setEndTime(LocalDateTime.now());
            logEntry.setDurationMs(Duration.between(startTime, logEntry.getEndTime()).toMillis());
            repo.save(logEntry);
        });

        log.info("✅ [{}] API Success | RefID={}", apiName, referenceId);
    }

    private void handleFailure(Throwable ex, String referenceId, String apiName, LocalDateTime startTime) {

        repo.findById(referenceId).ifPresent(logEntry -> {
            logEntry.setErrorMessage(ex.getMessage());
            logEntry.setHttpStatus(500);
            logEntry.setSuccess(false);
            logEntry.setEndTime(LocalDateTime.now());
            logEntry.setDurationMs(Duration.between(startTime, logEntry.getEndTime()).toMillis());
            repo.save(logEntry);
        });

        log.error("❌ [{}] API Failure | RefID={} | Error={}", apiName, referenceId, ex.getMessage());
    }

   /** Extract referenceId from JSON payload */
   /** Extract referenceId from JSON payload */
private String extractReferenceId(String json) {

    if (json == null || json.isBlank()) {
        return null;
    }

    log.info("JSON ::"+json);
    try {
        log.info("BEFORE MAPPING!!");
        JsonNode node = null;
        try{
            node = mapper.readTree(json);
        } 
        catch(Exception ex)
        {
            log.error("Node Exception ::"+ex);
        }
        
        log.info("JSON ROOT :: " + node.toString());

        String[] keys = { "referenceId", "referenceNo", "refId", "ref_no", "referenceNumber", "id" };

        // Check root level
        for (String key : keys) {
            JsonNode valueNode = node.get(key);
            log.info("VALUE NODE::"+valueNode);
            log.info("CHECKING KEY :: " + key);

            if (valueNode != null && !valueNode.isNull()) {
                String value = valueNode.asText();
                log.info("VALUE :: " + value);

                if (!value.isBlank()) {
                    log.info("REFERENCE ID FOUND (" + key + "): " + value);
                    return value;
                }
            }
        }

        // Check nested "data" node
        if (node.has("data") && node.get("data").isObject()) {
            JsonNode data = node.get("data");

            for (String key : keys) {
                JsonNode valueNode = data.get(key);

                if (valueNode != null && !valueNode.isNull()) {
                    String value = valueNode.asText();

                    if (!value.isBlank()) {
                        System.out.println("REFERENCE ID FOUND IN data (" + key + "): " + value);
                        return value;
                    }
                }
            }
        }

        System.out.println("NO REFERENCE ID FOUND IN JSON");
        return null;

    } catch (Exception e) {
        System.out.println("JSON PARSE ERROR: " + e.getMessage());
        return null;
    }
}


    private String extractUrl(Object[] args) {
        return args.length > 0 ? String.valueOf(args[0]) : "";
    }

    private String extractPayload(Object[] args) {
        return args.length > 1 ? toJson(args[1]) : null;
    }

    private String extractHeaders(Object[] args) {
        return args.length > 2 ? toJson(args[2]) : null;
    }

    private String toJson(Object data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            return String.valueOf(data);
        }
    }
}

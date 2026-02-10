package com.api.elifeconnect.Utility;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.api.elifeconnect.aop.LogExternalCall;
import com.api.elifeconnect.exception.ExternalApiException1;
import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Component
public class WebClientUtil {

    private final WebClient webClient;
    private final String apiKey;
    private final String apiKeyHeader;
    private final ObjectMapper objectMapper;

    /* ===================== Constructor ===================== */
     public WebClientUtil(
            WebClient.Builder builder,
            @Value("${elife.api.key}") String apiKey,
            @Value("${elife.api.keyHeader}") String apiKeyHeader,
            ObjectMapper objectMapper) {

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(cfg -> cfg.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                .build();

        this.webClient = builder
                .exchangeStrategies(strategies)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        this.apiKey = apiKey;
        this.apiKeyHeader = apiKeyHeader;
        this.objectMapper = objectMapper;
    }
    /* ===================== POST ===================== */
    @LogExternalCall("POST external API")
    public <T, R> Mono<R> post(
            String url,
            T body,
            Map<String, String> headers,
            Class<R> responseType) {

        // try {
        // log.info("FINAL REQUEST JSON :: {}",
        // new ObjectMapper().writeValueAsString(body));
        // } catch (JsonProcessingException ex) {
        // System.getLogger(WebClientUtil.class.getName()).log(System.Logger.Level.ERROR,
        // (String) null, ex);
        // }
        
        return webClient.post()
                .uri(url)
                .headers(h -> applyHeaders(h, headers))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::mapError)
                .bodyToMono(responseType)
                .retryWhen(retryPolicy())
                .timeout(Duration.ofSeconds(10));
    }

    /* ===================== GET ===================== */
    @LogExternalCall("GET external API")
    public <R> Mono<R> get(
            String url,
            Map<String, String> headers,
            Class<R> responseType) {

        return webClient.get()
                .uri(url)
                .headers(h -> applyHeaders(h, headers))
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::mapError)
                .bodyToMono(responseType)
                .retryWhen(retryPolicy())
                .timeout(Duration.ofSeconds(10));
    }

    /* ===================== Retry Policy ===================== */
    private Retry retryPolicy() {
        return Retry.backoff(2, Duration.ofMillis(500))
                .filter(ex -> !(ex instanceof ExternalApiException1))
                .onRetryExhaustedThrow((spec, signal) -> signal.failure());
    }

    /* ===================== Headers ===================== */
    private void applyHeaders(HttpHeaders headers, Map<String, String> extraHeaders) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(apiKeyHeader, apiKey);

        if (extraHeaders != null && !extraHeaders.isEmpty()) {
            extraHeaders.forEach(headers::add);
        }
    }

    /* ===================== Error Mapping ===================== */
private Mono<? extends Throwable> mapError(ClientResponse response) {

    HttpStatusCode statusCode = response.statusCode();
    int status = statusCode.value();

    String reason = (statusCode instanceof HttpStatus hs)
            ? hs.getReasonPhrase()
            : statusCode.toString();

    HttpHeaders headers = response.headers().asHttpHeaders();

    String uri = response.request() != null && response.request().getURI() != null
            ? response.request().getURI().toString()
            : "unknown";

    String method = response.request() != null && response.request().getMethod() != null
            ? response.request().getMethod().name()
            : "unknown";

return response.bodyToMono(byte[].class)
        .defaultIfEmpty(new byte[0])
        .flatMap(bodyBytes -> {

            String extractedMessage = extractErrorMessage(bodyBytes);

            ExternalApiException1 exception = new ExternalApiException1(
                    extractedMessage != null ? extractedMessage : "External API error",
                    status,
                    reason,
                    headers,
                    bodyBytes,
                    uri,
                    method
            );

            return Mono.error(exception);
        });

}

    /**
     * Upload PDF along with JSON data using Multipart
     */
    @LogExternalCall("UPLOAD PDF with JSON")
    public <R> Mono<R> uploadPdfWithJson(String url, MultiValueMap<String, HttpEntity<?>> multipartData,
            Map<String, String> headers, Class<R> responseType) {

        // Merge API key → always add
        Map<String, String> finalHeaders = new HashMap<>();
        if (headers != null) {
            finalHeaders.putAll(headers);
        }
        finalHeaders.put(apiKeyHeader, apiKey);

        return webClient.post()
                .uri(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .headers(h -> finalHeaders.forEach(h::add))
                .body(org.springframework.web.reactive.function.BodyInserters
                        .fromMultipartData(multipartData))
                .retrieve()
                .onStatus(status -> status.isError(), this::mapError)
                .bodyToMono(responseType)
                .retryWhen(Retry.backoff(2, Duration.ofMillis(500)))
                .timeout(Duration.ofSeconds(30)); // Uploads might take longer
    }

    /**
 * Download PDF by sending JSON data
 * - SUCCESS  → application/pdf → byte[]
 * - FAILURE  → application/json → ExternalApiException1
 */
@LogExternalCall("DOWNLOAD PDF")
public <T> Mono<byte[]> downloadPdf(
        String url,
        T requestBody,
        Map<String, String> headers
) {

    Map<String, String> finalHeaders = new HashMap<>();
    if (headers != null) {
        finalHeaders.putAll(headers);
    }
    finalHeaders.put(apiKeyHeader, apiKey);

    return webClient.post()
            .uri(url)
            .headers(h -> {
                finalHeaders.forEach(h::add);
                h.setContentType(MediaType.APPLICATION_JSON);
                h.setAccept(
                        java.util.List.of(
                                MediaType.APPLICATION_PDF,
                                MediaType.APPLICATION_JSON
                        )
                );
            })
            .bodyValue(requestBody)
            .exchangeToMono(response -> {

                MediaType contentType = response.headers()
                        .contentType()
                        .orElse(MediaType.APPLICATION_OCTET_STREAM);

                // ✅ SUCCESS → PDF
                if (response.statusCode().is2xxSuccessful()
                        && MediaType.APPLICATION_PDF.isCompatibleWith(contentType)) {

                    return response.bodyToMono(byte[].class);
                }

                // ❌ ERROR → JSON or anything else
                return response.bodyToMono(byte[].class)
                        .defaultIfEmpty(new byte[0])
                        .flatMap(bodyBytes -> {

                            String extractedMessage = extractErrorMessage(bodyBytes);

                            ExternalApiException1 ex =
                                    new ExternalApiException1(
                                            extractedMessage != null
                                                    ? extractedMessage
                                                    : "Failed to download PDF",
                                            response.statusCode().value(),
                                            response.statusCode().toString(),
                                            response.headers().asHttpHeaders(),
                                            bodyBytes,
                                            url,
                                            "POST"
                                    );

                            return Mono.error(ex);
                        });
            })
            .retryWhen(retryPolicy())
            .timeout(Duration.ofSeconds(30));
}

    /**
     * Generic wrapper to call GET or POST dynamically
     */
    public <T, R> Mono<R> callApi(String requestType, String url, T body, Map<String, String> headers,
            Class<R> responseType) {
        if ("POST".equalsIgnoreCase(requestType)) {
            return post(url, body, headers, responseType);
        } else if ("GET".equalsIgnoreCase(requestType)) {
            return get(url, headers, responseType);
        } else {
            return Mono.error(new IllegalArgumentException("Unsupported request type: " + requestType));
        }
    }

    /**
     * Generic wrapper with dynamic response mapping based on status code
     */
    public <T> Mono<Object> callApiWithMapping(
            String requestType,
            String url,
            T body,
            Map<String, String> headers,
            Map<Integer, Class<?>> statusMapper,
            Class<?> defaultType) {

        // Merge API key → always add
        Map<String, String> finalHeaders = new HashMap<>();
        if (headers != null) {
            finalHeaders.putAll(headers);
        }
        finalHeaders.put(apiKeyHeader, apiKey);

        WebClient.RequestHeadersSpec<?> requestSpec;

        if ("POST".equalsIgnoreCase(requestType)) {
            WebClient.RequestBodySpec postSpec = webClient.post().uri(url);
            if (body != null) {
                postSpec.bodyValue(body);
            }
            requestSpec = postSpec;
        } else if ("GET".equalsIgnoreCase(requestType)) {
            requestSpec = webClient.get().uri(url);
        } else {
            return Mono.error(new IllegalArgumentException("Unsupported request type: " + requestType));
        }

        requestSpec.headers(h -> finalHeaders.forEach(h::add));

        return requestSpec.exchangeToMono(response -> {

            int status = response.statusCode().value();
            Class<?> targetClass = statusMapper.getOrDefault(status, defaultType);

            // SUCCESS CASE
            if (!response.statusCode().isError()) {
                if (targetClass == null) {
                    return Mono.empty();
                }
                return response.bodyToMono(targetClass).map(obj -> (Object) obj);
            }

            // ERROR CASE → convert to error signal
            return mapError(response).flatMap(Mono::error);
        })
                .retryWhen(retryPolicy())
                .timeout(Duration.ofSeconds(10));
    }

    private String extractErrorMessage(byte[] bodyBytes) {

    if (bodyBytes == null || bodyBytes.length == 0) {
        return null;
    }

    try {
        JsonNode root = objectMapper.readTree(bodyBytes);

        // Most common patterns
        if (root.has("message")) {
            return root.get("message").asText();
        }
        if (root.has("error")) {
            return root.get("error").asText();
        }
        if (root.has("errors") && root.get("errors").isArray()
                && root.get("errors").size() > 0) {
            return root.get("errors").get(0).asText();
        }

    } catch (Exception ignored) {
        // Body is not JSON → ignore safely
    }

    return null;
}


}

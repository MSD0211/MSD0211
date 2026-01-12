package com.api.elifeconnect.exception;

import org.springframework.http.HttpHeaders;
import lombok.Getter;

@Getter
public class ExternalApiException1 extends RuntimeException {

    private final int statusCode;
    private final String statusText;
    private final HttpHeaders headers;
    private final String responseBody;
    private final String requestUrl;
    private final String httpMethod;

    public ExternalApiException1(
            String message,
            int statusCode,
            String statusText,
            HttpHeaders headers,
            byte[] responseBodyBytes,
            String requestUrl,
            String httpMethod) {

        super(message);
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.headers = headers;
        this.responseBody = responseBodyBytes != null ? new String(responseBodyBytes) : "";
        this.requestUrl = requestUrl;
        this.httpMethod = httpMethod;
    }
}

package com.api.elifeconnect.exception;

import org.springframework.http.HttpHeaders;
import lombok.Getter;

@Getter
public class ExternalApiException1 extends RuntimeException {

    private final int status;
    private final String reason;
    private final HttpHeaders headers;
    private final byte[] responseBody;
    private final String uri;
    private final String method;

    public ExternalApiException1(
            String message,
            int status,
            String reason,
            HttpHeaders headers,
            byte[] responseBody,
            String uri,
            String method) {

        super(message);
        this.status = status;
        this.reason = reason;
        this.headers = headers;
        this.responseBody = responseBody;
        this.uri = uri;
        this.method = method;
    }
}

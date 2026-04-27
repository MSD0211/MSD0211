package com.api.elifeconnect.exception;

public class ExternalServiceUnavailableException extends ExternalApiException {
    public ExternalServiceUnavailableException(Object errorBody) {
        super("External API unavailable", 503, errorBody);
    }
}

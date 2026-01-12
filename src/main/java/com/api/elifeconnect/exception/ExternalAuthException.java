package com.api.elifeconnect.exception;

public class ExternalAuthException extends ExternalApiException {
    public ExternalAuthException(Object errorBody) {
        super("External API authentication failed", 401, errorBody);
    }
}

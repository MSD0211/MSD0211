package com.api.elifeconnect.exception;

public class ExternalUnknownException extends ExternalApiException {
    public ExternalUnknownException(int status, Object errorBody) {
        super("External API returned unexpected error", status, errorBody);
    }
}

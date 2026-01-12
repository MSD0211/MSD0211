package com.api.elifeconnect.exception;

public class ExternalBadRequestException extends ExternalApiException {
    public ExternalBadRequestException(Object errorBody) {
        super("External API bad request", 400, errorBody);
    }
}


package com.api.elifeconnect.exception;

public class ExternalApiException extends RuntimeException {

    private int statusCode;
    private Object errorBody;

    public ExternalApiException(String message, int statusCode, Object errorBody) {
        super(message);
        this.statusCode = statusCode;
        this.errorBody = errorBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Object getErrorBody() {
        return errorBody;
    }
}

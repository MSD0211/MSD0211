package com.api.elifeconnect.common.response;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ApiResponseBuilder {

    public <T> ApiResponse<T> success(HttpServletRequest request, String message, T data) {
        return ApiResponse.success(request.getRequestURI(), message, data);
    }

    public <T> ApiResponse<T> failure(HttpServletRequest request, int status, List<String> errors) {
        return ApiResponse.failure(request.getRequestURI(), status, errors);
    }
}

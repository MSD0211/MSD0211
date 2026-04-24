package com.api.elifeconnect.common.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
        String timestamp,
        String path,
        String requestId,
        int status,
        boolean success,
        String message,
        T data,
        List<String> errors
) {

    public static <T> ApiResponse<T> success(String path, String message, T data) {
        return new ApiResponse<>(
                Instant.now().toString(),
                path,
                UUID.randomUUID().toString(),   // Keep requestId for frontend debug
                HttpStatus.OK.value(),
                true,
                message,
                data,
                List.of()
        );
    }

    public static <T> ApiResponse<T> failure(String path, int status, List<String> errors) {
        return new ApiResponse<>(
                Instant.now().toString(),
                path,
                UUID.randomUUID().toString(),
                status,
                false,
                "Request failed",
                null,
                errors
        );
    }

    public static <T> ApiResponse<T> failureWithData(String path, int status, T data, List<String> errors) {
        return new ApiResponse<>(
                Instant.now().toString(),
                path,
                UUID.randomUUID().toString(),
                status,
                false,
                "Request failed",
                data,
                errors
        );
    }
}

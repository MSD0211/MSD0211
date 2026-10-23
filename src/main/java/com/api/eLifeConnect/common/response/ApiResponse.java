package com.api.elifeconnect.common.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard API response envelope")
public record ApiResponse<T>(
        @Schema(description = "Timestamp when response was generated", example = "2026-10-16T11:45:00Z")
        String timestamp,

        @Schema(description = "Target API endpoint path", example = "/api/v1/lanka/policy/enquiry/customer")
        String path,

        @Schema(description = "Unique trace/debug request identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
        String requestId,

        @Schema(description = "HTTP status code", example = "200")
        int status,

        @Schema(description = "Indicates whether operation was successful", example = "true")
        boolean success,

        @Schema(description = "Descriptive outcome message", example = "Request processed successfully")
        String message,

        @Schema(description = "Payload data of type T")
        T data,

        @Schema(description = "List of errors if request was unsuccessful", example = "[]")
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

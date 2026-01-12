package com.api.elifeconnect.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;

import com.api.elifeconnect.common.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle WebClient custom errors (external API errors)
     */
    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<ApiResponse<Object>> handleWebClientException(
            ExternalApiException ex,
            HttpServletRequest request) {

      ApiResponse<Object> response = ApiResponse.failure(
                request.getRequestURI(),
                ex.getStatusCode(),
                List.of(
                        ex.getMessage(),
                        ex.getErrorBody() != null ? ex.getErrorBody().toString() : "No error body"
                )
        );

        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }

    /**
     * Handle validation errors: @Valid, @NotNull, etc.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .toList();

        ApiResponse<Object> response = ApiResponse.failure(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handle all other runtime exceptions
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        ex.printStackTrace(); // Optional: remove in production

        ApiResponse<Object> response = ApiResponse.failure(
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                List.of(ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

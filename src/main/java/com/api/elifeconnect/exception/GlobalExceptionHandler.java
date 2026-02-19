package com.api.elifeconnect.exception;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import com.api.elifeconnect.common.response.ValidationErrorData;

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

        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fe -> fe.getField(),
                        fe -> fe.getDefaultMessage(),
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        ValidationErrorData data = new ValidationErrorData(fieldErrors, List.of());

        ApiResponse<Object> response = ApiResponse.failureWithData(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                data,
                List.of()
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(org.springframework.validation.BindException.class)
    public ResponseEntity<ApiResponse<Object>> handleBindException(org.springframework.validation.BindException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fe -> fe.getField(),
                        fe -> fe.getDefaultMessage(),
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        ValidationErrorData data = new ValidationErrorData(fieldErrors, List.of());

        ApiResponse<Object> response = ApiResponse.failureWithData(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                data,
                List.of()
        );

        return ResponseEntity.badRequest().body(response);
    }

        @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
        public ResponseEntity<ApiResponse<Object>> handleConstraintViolation(jakarta.validation.ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, String> violations = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        cv -> cv.getPropertyPath().toString(),
                        cv -> cv.getMessage(),
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        ValidationErrorData data = new ValidationErrorData(violations, List.of());

        ApiResponse<Object> response = ApiResponse.failureWithData(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                data,
                List.of()
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
                HttpStatus.BAD_REQUEST.value(),
                List.of(ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

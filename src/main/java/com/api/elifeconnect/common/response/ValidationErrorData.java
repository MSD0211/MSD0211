package com.api.elifeconnect.common.response;

import java.util.List;
import java.util.Map;

/**
 * Structured validation error payload returned in ApiResponse.data
 */
public record ValidationErrorData(
        Map<String, String> fieldErrors,
        List<String> violations
) {
}

package com.api.elifeconnect.dto.policy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PolicyDetailsRequest(

        @NotBlank(message = "Reference ID is required") @Size(max = 50, message = "Reference ID cannot exceed 50 characters") String referenceId,

        @NotBlank(message = "Policy Number is required") @Pattern(regexp = "\\d+", message = "Policy Number must contain only digits") String policyNumber

) {
}
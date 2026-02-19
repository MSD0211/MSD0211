package com.api.elifeconnect.dto.policy;

import jakarta.validation.constraints.NotBlank;

public record CustomerPolicyEnquiryRequest(
        @NotBlank(message = "referenceId is required")
        String referenceId,

        @NotBlank(message = "custId is required")
        String custId
) {
}
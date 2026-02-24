package com.api.elifeconnect.dto.policy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerPolicyEnquiryRequest(
        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        String referenceId,

        @NotBlank(message = "custId is required")
        @Size(max = 9)
        String custId
) {
}
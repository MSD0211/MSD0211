package com.api.elifeconnect.dto.premium.summary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PremiumSummaryRequest(

        @NotNull(message = "referenceId must not be null")
        @NotBlank(message = "referenceId must not be blank")
        String referenceId,

        @NotNull(message = "policyNumber must not be null")
        @NotBlank(message = "policyNumber must not be blank")
        String policyNumber
) {
}

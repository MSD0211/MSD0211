package com.api.elifeconnect.dto.premium.summary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PremiumSummaryRequest(

        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        String referenceId,

        @NotBlank(message = "policyNumber is required")
        @Size(max = 9)
        String policyNumber
) {
}

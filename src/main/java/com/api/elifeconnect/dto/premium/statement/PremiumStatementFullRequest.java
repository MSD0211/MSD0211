package com.api.elifeconnect.dto.premium.statement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PremiumStatementFullRequest(

        @NotBlank(message = "referenceId must not be blank")
        @Size(max = 30)
        String referenceId,

        @NotBlank(message = "policyNumber must not be blank")
        @Size(max = 9)
        String policyNumber
) {
}

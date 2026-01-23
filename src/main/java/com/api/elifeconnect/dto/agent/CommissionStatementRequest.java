package com.api.elifeconnect.dto.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CommissionStatementRequest(

        @NotNull(message = "referenceId must not be null")
        @NotBlank(message = "referenceId must not be blank")
        String referenceId,

        @NotNull(message = "agencyCode must not be null")
        @NotBlank(message = "agencyCode must not be blank")
        String agencyCode,

        @NotNull(message = "billMonth must not be null")
        @NotBlank(message = "billMonth must not be blank")
        String billMonth,

        @NotNull(message = "billYear must not be null")
        @NotBlank(message = "billYear must not be blank")
        String billYear
) {
}


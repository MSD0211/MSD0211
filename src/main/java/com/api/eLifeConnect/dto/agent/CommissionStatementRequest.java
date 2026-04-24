package com.api.elifeconnect.dto.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record CommissionStatementRequest(

        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        String referenceId,

        @NotBlank(message = "agencyCode is required")
        @Size(max = 9)
        String agencyCode,

        @NotBlank(message = "billMonth is required")
        @Size(max = 2)
        @Pattern(regexp = "\\d+", message = "billMonth must be numeric")
        String billMonth,

        @NotBlank(message = "billYear is required")
        @Size(max = 4)
        @Pattern(regexp = "\\d+", message = "billYear must be numeric")
        String billYear
) {
}


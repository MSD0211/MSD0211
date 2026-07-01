package com.api.elifeconnect.dto.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for downloading commission statement PDF")
public record CommissionStatementRequest(

        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        @Schema(description = "Unique reference ID for tracing the request", example = "REF987654321")
        String referenceId,

        @NotBlank(message = "agencyCode is required")
        @Size(max = 9)
        @Schema(description = "Agency code of the agent", example = "AGE001")
        String agencyCode,

        @NotBlank(message = "billMonth is required")
        @Size(max = 2)
        @Pattern(regexp = "\\d+", message = "billMonth must be numeric")
        @Schema(description = "Billing month (2 digits, numeric format)", example = "05")
        String billMonth,

        @NotBlank(message = "billYear is required")
        @Size(max = 4)
        @Pattern(regexp = "\\d+", message = "billYear must be numeric")
        @Schema(description = "Billing year (4 digits, numeric format)", example = "2026")
        String billYear
) {
}


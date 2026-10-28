package com.api.elifeconnect.dto.policy;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for customer policy enquiry")
public record CustomerPolicyEnquiryRequest(
        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        @Schema(description = "Unique reference ID for request tracing", example = "REF-20261016-001")
        String referenceId,

        @NotBlank(message = "custId is required")
        @Size(max = 9)
        @Schema(description = "Customer ID", example = "CUST12345")
        String custId
) {
}
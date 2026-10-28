package com.api.elifeconnect.dto.premium;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for renewal premium enquiry")
public record RenewalPremiumEnquiryRequest(

        @NotBlank(message = "policyNo is required")
        @Size(max = 9)
        @Schema(description = "Policy number", example = "POL123456")
        String policyNo,

        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        // @JsonProperty("referenceId")
        @JsonAlias({"referenceId"})
        @Schema(description = "Unique reference ID for request tracing", example = "REF-20261016-003")
        String referenceNo,

        @Schema(description = "Enquiry type (defaults to '1')", example = "1", defaultValue = "1")
        String type
) {
    public RenewalPremiumEnquiryRequest {
        type = (type == null) ? "1" : type;
    }
}

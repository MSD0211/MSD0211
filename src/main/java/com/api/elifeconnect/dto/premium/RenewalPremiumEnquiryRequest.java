package com.api.elifeconnect.dto.premium;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RenewalPremiumEnquiryRequest(

        @NotBlank(message = "policyNo is required")
        @Size(max = 9)
        String policyNo,

        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        // @JsonProperty("referenceId")
        @JsonAlias({"referenceId"})
        String referenceNo,

        String type
) {
    public RenewalPremiumEnquiryRequest {
        type = (type == null) ? "1" : type;
    }
}

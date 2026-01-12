package com.api.elifeconnect.dto.premium;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record RenewalPremiumEnquiryRequest(

        @NotBlank
        String policyNo,

        @NotBlank
        // @JsonProperty("referenceId")
        @JsonAlias({"referenceId"})
        String referenceNo,

        String type
) {
    public RenewalPremiumEnquiryRequest {
        type = (type == null) ? "1" : type;
    }
}

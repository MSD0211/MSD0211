package com.api.elifeconnect.dto.premium;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record RenewalPremiumAdjustmentRequest(

        @JsonProperty("policy_no")
        @NotBlank String policyNo,

        @NotBlank String amount,

        @NotBlank String phone,

        @NotBlank String name,

        @NotBlank String shortCode,

        @NotBlank String date,

        @NotBlank String type,

        @NotBlank String referenceNo
) {
        public RenewalPremiumAdjustmentRequest {
        shortCode = (shortCode == null) ? "1" : shortCode;
        type = (type == null) ? "1" : type;
    }
}

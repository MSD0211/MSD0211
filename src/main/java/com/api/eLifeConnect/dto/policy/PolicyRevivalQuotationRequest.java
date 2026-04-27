package com.api.elifeconnect.dto.policy;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PolicyRevivalQuotationRequest(

        @JsonAlias("referenceId")
        @NotBlank(message = "Reference Id is required")
        @Size(max = 30)
        String referenceNo,

        @JsonAlias("policyNumber")
        @NotBlank(message = "Policy Number is required")
        @Size(max = 9)
        @NotBlank String policyNo

) {
}

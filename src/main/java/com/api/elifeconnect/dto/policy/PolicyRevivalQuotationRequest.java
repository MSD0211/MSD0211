package com.api.elifeconnect.dto.policy;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;

public record PolicyRevivalQuotationRequest(

        @JsonAlias("referenceId")
        @NotBlank String referenceNo,

        @JsonAlias("policyNumber")
        @NotBlank String policyNo

) {
}

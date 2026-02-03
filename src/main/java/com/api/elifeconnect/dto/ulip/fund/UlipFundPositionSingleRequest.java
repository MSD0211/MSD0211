package com.api.elifeconnect.dto.ulip.fund;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;

public record UlipFundPositionSingleRequest(

        @NotBlank(message = "Reference Id must not be blank")
        @JsonAlias({"referenceId"})
        String referenceNo,

        @NotBlank(message = "Policy number must not be blank")
        @JsonAlias({"policyNumber"})
        String policyNo

) {
}


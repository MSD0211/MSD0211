package com.api.elifeconnect.dto.ulip.fund;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UlipFundPositionSingleRequest(

        @NotBlank(message = "Reference Id is required")
        @Size(max = 30)
        @JsonAlias({"referenceId"})
        String referenceNo,

        @NotBlank(message = "Policy number is required")
        @JsonAlias({"policyNumber"})
        String policyNo

) {
}


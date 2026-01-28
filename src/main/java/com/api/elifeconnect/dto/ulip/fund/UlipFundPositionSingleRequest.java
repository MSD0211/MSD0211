package com.api.elifeconnect.dto.ulip.fund;

import jakarta.validation.constraints.NotBlank;

public record UlipFundPositionSingleRequest(

        @NotBlank(message = "Reference Id must not be blank")
        String referenceId,

        @NotBlank(message = "Policy number must not be blank")
        String policyNumber,

        boolean allowDelayedPayInterest
) {
}


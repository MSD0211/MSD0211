package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;

public record AddressOfCommunication(
        @NotBlank(message = "mobile is required")
        String mob,

        String tel,

        @NotBlank(message = "line1 is required")
        String line1,

        String line2,

        String line3,

        String line4
) {}

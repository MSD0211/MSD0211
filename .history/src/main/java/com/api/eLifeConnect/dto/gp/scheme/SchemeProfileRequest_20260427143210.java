package com.api.elifeconnect.dto.gp.scheme;

import jakarta.validation.constraints.NotBlank;

public record SchemeProfileRequest(

        @NotBlank(message = "Reference ID is required")
        String referenceId,

        @NotBlank(message = "Scheme number is required")
        String schemeNumber

) {}
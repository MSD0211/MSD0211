package com.api.elifeconnect.dto.gp.scheme;

import jakarta.validation.constraints.NotBlank;

public record SchemeProfileRequest(

        @NotBlank(message = "Scheme number is required")
        String schemeNumber

) {}
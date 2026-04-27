package com.api.elifeconnect.dto.gp.scheme;

import jakarta.validation.constraints.NotBlank;

public record SchemeMemberDetailsRequest(

        @NotBlank(message = "Scheme number is required")
        String schemeNumber

) {}
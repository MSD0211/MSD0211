package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;

public record MerDetails(
        @NotBlank(message = "bp is required")
        String bp,

        String abdomen,

        String pulse_rate,

        String chest_expiration,

        String chest_inspiration
) {}

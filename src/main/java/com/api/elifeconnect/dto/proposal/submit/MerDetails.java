package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MerDetails(

        /* Blood Pressure – Mandatory */
        @NotBlank(message = "bp is required")
        @Size(max = 10)
        String bp,

        /* Abdomen – NOT mandatory */
        @Size(max = 10)
        String abdomen,

        /* Pulse Rate – NOT mandatory, Numeric */
        @Pattern(regexp = "\\d*", message = "pulse_rate must be numeric")
        @Size(max = 5)
        String pulse_rate,

        /* Chest Expiration – NOT mandatory, Numeric */
        @Pattern(regexp = "\\d*(\\.\\d+)?", message = "chest_expiration must be numeric")
        @Size(max = 5)
        String chest_expiration,

        /* Chest Inspiration – NOT mandatory, Numeric */
        @Pattern(regexp = "\\d*(\\.\\d+)?", message = "chest_inspiration must be numeric")
        @Size(max = 5)
        String chest_inspiration
) {}
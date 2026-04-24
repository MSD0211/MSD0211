package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FamilyHistory(

        /* Age – Mandatory, Numeric */
        @NotBlank(message = "age is required")
        @Pattern(regexp = "\\d+", message = "age must be numeric")
        @Size(max = 3)
        String age,

        /* Description – Mandatory */
        @NotBlank(message = "description is required")
        @Size(max = 100)
        String description,

        /* Relationship – Mandatory, Allowed values */
        @NotBlank(message = "relationship is required")
        @Pattern(
            regexp = "F|M|B|S|D|H|W|O",
            message = "relationship must be one of F,M,B,S,D,H,W,O"
        )
        @Size(max = 1)
        String relationship,

        /* Vital Status – Mandatory, Allowed values */
        @NotBlank(message = "vital_status is required")
        @Pattern(
            regexp = "A|D",
            message = "vital_status must be A (Alive) or D (Dead)"
        )
        @Size(max = 1)
        String vital_status
) {}
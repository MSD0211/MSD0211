package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;

public record FamilyHistory(
        @NotBlank(message = "age is required")
        String age,

        @NotBlank(message = "description is required")
        String description,

        @NotBlank(message = "relationship is required")
        String relationship,

        @NotBlank(message = "vital_status is required")
        String vital_status
) {}

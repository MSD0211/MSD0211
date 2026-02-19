package com.api.elifeconnect.dto.plan.Details;

import jakarta.validation.constraints.NotBlank;

public record PlanDetailsRequest(
        @NotBlank(message = "referenceId is required")
        String referenceId,

        @NotBlank(message = "dob is required")
        String dob,

        @NotBlank(message = "doc is required")
        String doc,

        @NotBlank(message = "gender is required")
        String gender,

        @NotBlank(message = "ageProof is required")
        String ageProof
) {}

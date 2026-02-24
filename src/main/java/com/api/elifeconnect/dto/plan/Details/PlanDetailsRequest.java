package com.api.elifeconnect.dto.plan.Details;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PlanDetailsRequest(

        /* Reference ID – Mandatory */
        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        String referenceId,

        /* Date of Birth – Mandatory, YYYY-MM-DD */
        @NotBlank(message = "dob is required")
        @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "dob must be in YYYY-MM-DD format"
        )
        String dob,

        /* Date of Commencement – Mandatory, YYYY-MM-DD */
        @NotBlank(message = "doc is required")
        @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "doc must be in YYYY-MM-DD format"
        )
        String doc,

        /* Gender – Mandatory, Allowed values */
        @NotBlank(message = "gender is required")
        @Pattern(
            regexp = "M|F",
            message = "gender must be M, F"
        )
        @Size(max = 1)
        String gender,

        /* Age Proof – Mandatory, Allowed values */
        @NotBlank(message = "ageProof is required")
        @Pattern(
            regexp = "M|S|D|E|R|P|C|L|N",
            message = "ageProof must be one of M,S,D,E,R,P,C,L,N"
        )
        @Size(max = 1)
        String ageProof
) {}
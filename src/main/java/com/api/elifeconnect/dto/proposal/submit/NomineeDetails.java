package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;

public record NomineeDetails(
        @NotBlank(message = "age is required")
        String age,

        @NotBlank(message = "dob is required")
        String dob,

        @NotBlank(message = "address is required")
        String address,

        @NotBlank(message = "relation is required")
        String relation,

        @NotBlank(message = "last_name is required")
        String last_name,

        @NotBlank(message = "first_name is required")
        String first_name,

        @NotBlank(message = "national_id is required")
        String national_id,

        @NotBlank(message = "nationality is required")
        String nationality,

        String passport_number,

        String local_phone_number,

        String passport_expiry_date,

        String national_id_expiry_date
) {}

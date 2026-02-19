package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;

public record PassportDetails(
        @NotBlank(message = "date_of_issue is required")
        String date_of_issue,

        @NotBlank(message = "date_of_expiry is required")
        String date_of_expiry,

        @NotBlank(message = "passport_number is required")
        String passport_number,

        @NotBlank(message = "country_of_issue is required")
        String country_of_issue
) {}

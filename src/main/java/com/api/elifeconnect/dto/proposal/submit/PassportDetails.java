package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PassportDetails(

        @NotBlank(message = "Passport date of issue is required")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of Issue must be YYYY-MM-DD")
        String date_of_issue,

        @NotBlank(message = "Passport date of expiry is mandatory")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Expiry date must be YYYY-MM-DD")
        String date_of_expiry,

        @NotBlank(message = "Passport Number is required")
        @Size(max = 20)
        String passport_number,

        @NotBlank(message = "Passport Country of Issue is required")
        @Size(max = 60)
        String country_of_issue
) {}

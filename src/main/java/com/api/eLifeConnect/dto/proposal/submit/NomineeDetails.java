package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public record NomineeDetails(

        /* Nominee Age – Mandatory, Numeric */
        @NotNull(message = "Nominee age is mandatory")
        @Min(value = 0, message = "Nominee age must be >= 0")
        @Max(value = 120, message = "Nominee age must be <= 120")
        Integer age,

        /* Nominee DOB – Mandatory */
        @NotBlank(message = "Nominee DOB is mandatory")
        @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "DOB must be YYYY-MM-DD"
        )
        String dob,

        /* Nominee Address – Mandatory */
        @NotBlank(message = "Nominee address is mandatory")
        @Size(max = 100)
        String address,

        /* Nominee Relation – Mandatory */
        @NotBlank(message = "Nominee relation is mandatory")
        @Pattern(regexp = "B|D|F|H|M|O|R|S|W")
        @Size(max = 2)
        String relation,

        /* Nominee First Name – Mandatory */
        @NotBlank(message = "Nominee first name is mandatory")
        @Size(max = 75)
        String first_name,

        /* Nominee Last Name – Mandatory */
        @NotBlank(message = "Nominee last name is mandatory")
        @Size(max = 75)
        String last_name,

        /* Nominee National ID – Mandatory */
        @NotBlank(message = "Nominee national ID is mandatory")
        @Size(max = 15)
        String national_id,

        /* ---- NOT MANDATORY FIELDS (size / format only) ---- */

        @Size(max = 20)
        String nationality,

        @Size(max = 20)
        String passport_number,

        @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "passport_expiry_date must be YYYY-MM-DD"
        )
        String passport_expiry_date,

        @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "national_id_expiry_date must be YYYY-MM-DD"
        )
        String national_id_expiry_date,

        @Size(max = 15)
        @Pattern(regexp = "\\d+", message = "local_phone_number must be numeric")
        String local_phone_number
) {}
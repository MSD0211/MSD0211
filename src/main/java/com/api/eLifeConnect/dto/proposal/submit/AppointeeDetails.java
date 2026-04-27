
package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AppointeeDetails(

        @NotNull(message = "Appointee age is mandatory")
        Integer age,

        @NotBlank(message = "Appointee DOB is mandatory")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "DOB must be YYYY-MM-DD")
        String dob,

        @NotBlank(message = "Appointee address is mandatory")
        @Size(max = 100)
        String address,

        @NotBlank(message = "Appointee relation is mandatory")
        @Pattern(regexp = "B|D|F|H|M|O|R|S|W")
        @Size(max = 2)
        String relation,

        @NotBlank(message = "Appointee first name is mandatory")
        @Size(max = 75)
        String first_name,

        @NotBlank(message = "Appointee last name is mandatory")
        @Size(max = 75)
        String last_name,

        @NotBlank(message = "Appointee national ID is mandatory")
        @Size(max = 15)
        String national_id,

        /* ---- NOT MANDATORY FIELDS (size/format only) ---- */

        @Size(max = 20)
        String nationality,

        @Size(max = 20)
        String passport_number,

        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "YYYY-MM-DD expected")
        String passport_expiry_date,

        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "YYYY-MM-DD expected")
        String national_id_expiry_date,

        @Size(max = 15)
        @Pattern(regexp = "\\d+", message = "Phone must be numeric")
        String local_phone_number
) {}

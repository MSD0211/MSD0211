package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record HusbandInsurance(

        /* Sum Assured – Mandatory, Numeric */
        @NotBlank(message = "sum_assured is required")
        @Pattern(regexp = "\\d+", message = "sum_assured must be numeric")
        @Size(max = 12)
        String sum_assured,

        /* Plan and Term – NOT mandatory */
        @Size(max = 60)
        String plan_and_term,

        /* Policy Number – NOT mandatory */
        @Size(max = 60)
        String policy_number,

        /* Present Status – Allowed values */
        @Pattern(
            regexp = "A|I|L|M|S",
            message = "present_status must be A, I, L, M or S"
        )
        @Size(max = 1)
        String present_status,

        /* Insurance Company Name and Address – NOT mandatory */
        @Size(max = 255)
        String insurance_company_name_and_address
) {}
package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PreviousInsurance(

        /* Policy Term – Mandatory, Numeric */
        @NotBlank(message = "policy_term is required")
        @Pattern(regexp = "\\d+", message = "policy_term must be numeric")
        @Size(max = 3)
        String policy_term,

        /* Sum Assured – Mandatory, Numeric */
        @NotBlank(message = "sum_assured is required")
        @Pattern(regexp = "\\d+", message = "sum_assured must be numeric")
        @Size(max = 12)
        String sum_assured,

        /* Policy Number – NOT mandatory */
        @Size(max = 60)
        String policy_number,

        /* Year of Issue – NOT mandatory, YYYY */
        @Pattern(regexp = "\\d{4}", message = "year_of_issue must be YYYY")
        String year_of_issue,

        /* Accident Benefit – Y / N (NOT mandatory) */
        @Pattern(regexp = "Y|N", message = "accident_benefit must be Y or N")
        @Size(max = 1)
        String accident_benefit,

        /* Reason for Cover – NOT mandatory */
        @Size(max = 255)
        String reason_for_cover,

        /* Present Status of Policy – Allowed values */
        @Pattern(
            regexp = "A|I|L|M|S",
            message = "present_status_of_policy must be A, I, L, M or S"
        )
        @Size(max = 1)
        String present_status_of_policy,

        /* Name of Insurance Company – NOT mandatory */
        @Size(max = 75)
        String name_of_insurance_company
) {}
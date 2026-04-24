package com.api.elifeconnect.dto.proposal.submit;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record HealthStatements(

        /* MER Details – Mandatory */
        @NotNull(message = "mer is required")
        @Valid
        MerDetails mer,

        /* Height – Mandatory, Numeric */
        @NotBlank(message = "height is required")
        @Pattern(regexp = "\\d+(\\.\\d+)?", message = "height must be numeric")
        @Size(max = 5)
        String height,

        /* Weight – Mandatory, Numeric */
        @NotBlank(message = "weight is required")
        @Pattern(regexp = "\\d+(\\.\\d+)?", message = "weight must be numeric")
        @Size(max = 5)
        String weight,

        /* Smoker Status – Allowed values Y / N (NOT mandatory) */
        @Pattern(regexp = "Y|N", message = "smoker_status must be Y or N")
        @Size(max = 1)
        String smoker_status,

        /* Family History – Mandatory list (can be empty) */
        @NotNull(message = "family_history is required")
        @Size(min = 0)
        List<@Valid FamilyHistory> family_history,

        /* Husband Details – NOT mandatory */
        @Valid
        HusbandDetails husband_details,

        /* Husband Insurances – NOT mandatory */
        List<@Valid HusbandInsurance> husband_insurances,

        /* PF Health Questions – NOT mandatory */
        Map<String, String> pf_health_questions,

        /* Date of Last Delivery – YYYY-MM-DD (NOT mandatory) */
        @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "date_of_last_delivery must be YYYY-MM-DD"
        )
        String date_of_last_delivery,

        /* Date of Last Mensuration – YYYY-MM-DD (NOT mandatory) */
        @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "date_of_last_mensuration must be YYYY-MM-DD"
        )
        String date_of_last_mensuration,

        /* Male Health Questions – NOT mandatory */
        Map<String, String> pf_male_health_questions,

        /* Female Health Questions – NOT mandatory */
        Map<String, String> pf_female_health_questions,

        /* Willing to Answer Health Questions – Y / N */
        @Pattern(regexp = "Y|N", message = "is_willing_to_answer_health_questions must be Y or N")
        @Size(max = 1)
        String is_willing_to_answer_health_questions
) {}
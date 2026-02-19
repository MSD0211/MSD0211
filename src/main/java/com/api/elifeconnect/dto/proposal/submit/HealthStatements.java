package com.api.elifeconnect.dto.proposal.submit;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HealthStatements(
        @NotNull(message = "mer is required") @Valid MerDetails mer,

        @NotBlank(message = "height is required")
        String height,

        @NotBlank(message = "weight is required")
        String weight,

        String smoker_status,

        @NotNull(message = "family_history is required") @Size(min = 0)
        List<@Valid FamilyHistory> family_history,

        @Valid
        HusbandDetails husband_details,

        List<@Valid HusbandInsurance> husband_insurances,

        Map<String, String> pf_health_questions,

        String date_of_last_delivery,

        String date_of_last_mensuration,

        Map<String, String> pf_male_health_questions,

        Map<String, String> pf_female_health_questions,

        String is_willing_to_answer_health_questions
) {}

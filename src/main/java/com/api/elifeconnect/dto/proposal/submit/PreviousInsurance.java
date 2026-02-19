package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;

public record PreviousInsurance(
        @NotBlank(message = "policy_term is required")
        String policy_term,

        @NotBlank(message = "sum_assured is required")
        String sum_assured,

        String policy_number,

        String year_of_issue,

        String accident_benefit,

        String reason_for_cover,

        String present_status_of_policy,

        String name_of_insurance_company
) {}

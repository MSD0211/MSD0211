package com.api.elifeconnect.dto.proposal.submit;

public record PreviousInsurance(
        String policy_term,
        String sum_assured,
        String policy_number,
        String year_of_issue,
        String accident_benefit,
        String reason_for_cover,
        String present_status_of_policy,
        String name_of_insurance_company
) {}

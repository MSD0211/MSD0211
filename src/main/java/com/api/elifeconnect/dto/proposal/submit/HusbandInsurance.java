package com.api.elifeconnect.dto.proposal.submit;

public record HusbandInsurance(
        String sum_assured,
        String plan_and_term,
        String policy_number,
        String present_status,
        String insurance_company_name_and_address
) {}

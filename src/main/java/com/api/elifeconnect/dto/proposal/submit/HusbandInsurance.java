package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;

public record HusbandInsurance(
        @NotBlank(message = "sum_assured is required")
        String sum_assured,

        String plan_and_term,

        String policy_number,

        String present_status,

        String insurance_company_name_and_address
) {}

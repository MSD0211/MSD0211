package com.api.elifeconnect.dto.proposal.submit;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PfPlanDetails(
        @NotBlank(message = "mode is required")
        String mode,

        @NotBlank(message = "plan is required")
        String plan,

        @NotBlank(message = "term is required")
        String term,

        @NotBlank(message = "premium is required")
        String premium,

        String ci_rider_sa,

        @NotBlank(message = "sum_assured is required")
        String sum_assured,

        String hcb_rider_sa,

        String scb_rider_sa,

        String term_rider_sa,

        String insurance_rider,

        @NotNull(message = "nominee_details is required") @Valid
        NomineeDetails nominee_details,

        @Valid
        AppointeeDetails appointee_details,

        String health_rider_option,

        String premium_paying_term,

        String date_of_commencement,

        String critical_illness_rider,

        @NotNull(message = "proposer_1_previous_insurances is required") @Size(min = 0)
        List<@Valid PreviousInsurance> proposer_1_previous_insurances,

        @NotNull(message = "proposer_2_previous_insurances is required") @Size(min = 0)
        List<@Valid PreviousInsurance> proposer_2_previous_insurances,

        String proposer_1_purpose_of_insurance,

        String proposer_2_purpose_of_insurance,

        String proposer_1_have_previous_insurance,

        String proposer_2_have_previous_insurance,

        @Valid
        BankDetails bank_details_for_payment_of_premium,

        String sum_assured_for_critical_illness_rider,

        String sum_assured_required_for_double_accident_benefits
) {}

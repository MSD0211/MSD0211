package com.api.elifeconnect.dto.proposal.submit;

import java.util.List;

public record PfPlanDetails(
        String mode,
        String plan,
        String term,
        String premium,
        String ci_rider_sa,
        String sum_assured,
        String hcb_rider_sa,
        String scb_rider_sa,
        String term_rider_sa,
        String insurance_rider,
        NomineeDetails nominee_details,
        AppointeeDetails appointee_details,
        String health_rider_option,
        String premium_paying_term,
        String date_of_commencement,
        String critical_illness_rider,
        List<PreviousInsurance> proposer_1_previous_insurances,
        List<PreviousInsurance> proposer_2_previous_insurances,
        String proposer_1_purpose_of_insurance,
        String proposer_2_purpose_of_insurance,
        String proposer_1_have_previous_insurance,
        String proposer_2_have_previous_insurance,
        BankDetails bank_details_for_payment_of_premium,
        String sum_assured_for_critical_illness_rider,
        String sum_assured_required_for_double_accident_benefits
) {}

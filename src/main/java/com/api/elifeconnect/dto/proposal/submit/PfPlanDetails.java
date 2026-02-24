package com.api.elifeconnect.dto.proposal.submit;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PfPlanDetails(

        /* Policy Mode : Y / H / Q / M / N */
        @NotBlank(message = "mode is required")
        @Pattern(regexp = "Y|H|Q|M|N", message = "mode must be Y, H, Q, M or N")
        @Size(max = 1)
        String mode,

        /* Plan */
        @NotBlank(message = "plan is required")
        @Pattern(regexp = "\\d+", message = "plan must be numeric")
        @Size(max = 3)
        String plan,

        /* Policy Term */
        @NotBlank(message = "Policy Term is required")
        @Pattern(regexp = "\\d+", message = "term must be numeric")
        @Size(max = 3)
        String term,

        /* Premium Amount : decimal(12,2) */
        @NotBlank(message = "Premium is required")
        @Pattern(
            regexp = "\\d{1,12}(\\.\\d{1,2})?",
            message = "premium must be decimal(12,2)"
        )
        String premium,

        /* Critical Illness Rider Sum Assured – NOT mandatory */
        @Pattern(regexp = "\\d*", message = "ci_rider_sa must be numeric")
        String ci_rider_sa,

        /* Sum Assured */
        @NotBlank(message = "sum_assured is required")
        @Pattern(regexp = "\\d+", message = "sum_assured must be numeric")
        String sum_assured,

        /* Hospital Cash Benefit Rider SA – NOT mandatory */
        @Pattern(regexp = "\\d*", message = "hcb_rider_sa must be numeric")
        String hcb_rider_sa,

        /* Surgical Cash Benefit Rider SA – NOT mandatory */
        @Pattern(regexp = "\\d*", message = "scb_rider_sa must be numeric")
        String scb_rider_sa,

        /* Term Rider Sum Assured – NOT mandatory */
        @Pattern(regexp = "\\d*", message = "term_rider_sa must be numeric")
        String term_rider_sa,

        /* Insurance Rider – NOT mandatory */
        @Size(max = 60)
        String insurance_rider,

        /* Nominee Details – Mandatory */
        @NotNull(message = "nominee_details is required")
        @Valid
        NomineeDetails nominee_details,

        /* Appointee Details – NOT mandatory */
        @Valid
        AppointeeDetails appointee_details,

        /* Health Rider Option : 0 / 1 / 2 */
        @Pattern(regexp = "0|1|2", message = "health_rider_option must be 0, 1 or 2")
        @Size(max = 1)
        String health_rider_option,

        /* Premium Paying Term – NOT mandatory */
        @Pattern(regexp = "\\d*", message = "premium_paying_term must be numeric")
        String premium_paying_term,

        /* Date of Commencement : YYYY-MM-DD */
        @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "date_of_commencement must be YYYY-MM-DD"
        )
        String date_of_commencement,

        /* Critical Illness Rider – NOT mandatory */
        @Pattern(regexp = "\\d*", message = "critical_illness_rider must be numeric")
        String critical_illness_rider,

        /* Proposer 1 Previous Insurances – Mandatory list (can be empty) */
        @NotNull(message = "proposer_1_previous_insurances is required")
        @Size(min = 0)
        List<@Valid PreviousInsurance> proposer_1_previous_insurances,

        /* Proposer 2 Previous Insurances – Mandatory list (can be empty) */
        @NotNull(message = "proposer_2_previous_insurances is required")
        @Size(min = 0)
        List<@Valid PreviousInsurance> proposer_2_previous_insurances,

        /* Purpose of Insurance – NOT mandatory */
        @Size(max = 255)
        String proposer_1_purpose_of_insurance,

        @Size(max = 255)
        String proposer_2_purpose_of_insurance,

        /* Have Previous Insurance : Y / N */
        @Pattern(regexp = "Y|N", message = "must be Y or N")
        @Size(max = 1)
        String proposer_1_have_previous_insurance,

        @Pattern(regexp = "Y|N", message = "must be Y or N")
        @Size(max = 1)
        String proposer_2_have_previous_insurance,

        /* Bank Details – NOT mandatory */
        @Valid
        BankDetails bank_details_for_payment_of_premium,

        /* CI Rider Sum Assured – NOT mandatory */
        @Pattern(regexp = "\\d*", message = "sum_assured_for_critical_illness_rider must be numeric")
        String sum_assured_for_critical_illness_rider,

        /* Double Accident Benefit SA – NOT mandatory */
        @Pattern(regexp = "\\d*", message = "sum_assured_required_for_double_accident_benefits must be numeric")
        String sum_assured_required_for_double_accident_benefits
) {}
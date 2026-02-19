package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProposerDetails(
        @NotNull(message = "age is required")
        Object age,

        @NotBlank(message = "dob is required")
        String dob,

        @NotBlank(message = "gender is required")
        String gender,

        String email_id,

        String age_proof,

        String education,

        String occupation,

        String customer_id,

        String father_name,

        String national_id,

        String nationality,

        String employer_name,

        String marital_status,

        String place_of_birth,

        @Valid
        PassportDetails passport_details,

        String length_of_service,

        @NotBlank(message = "proposer_full_name is required")
        String proposer_full_name,

        String proposer_last_name,

        String proposer_first_name,

        String proposer_middle_name,

        String usual_state_of_health,

        String exact_nature_of_duties,

        String is_politically_exposed,

        String national_id_expiry_date,

        @Valid
        AddressOfCommunication address_of_communication,

        String annual_income_from_all_sources,

        String source_of_income_for_payment_of_premium
) {}

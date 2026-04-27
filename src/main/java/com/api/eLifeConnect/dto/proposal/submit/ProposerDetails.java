
package com.api.elifeconnect.dto.proposal.submit;

import com.api.elifeconnect.validation.groups.KenyaGroup;
import com.api.elifeconnect.validation.groups.LankaGroup;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProposerDetails(

        @NotNull(message = "Age is mandatory")
        Object age,

        @NotBlank(message = "Date of birth is mandatory")
        @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "DOB must be in YYYY-MM-DD format"
        )
        String dob,

        @NotBlank(message = "Gender is mandatory")
        @Pattern(regexp = "M|F", message = "Gender must be M or F")
        @Size(max = 3)
        String gender,

        @NotBlank(message = "Email ID is mandatory")
        @Email(message = "Invalid Email !!")
        @Size(max = 150)
        String email_id,

        @NotBlank(message = "Age proof is mandatory")
        @Pattern(
            regexp = "M|S|D|E|R|P|C|L|N",
            message = "ageProof must be one of M,S,D,E,R,P,C,L,N",
            groups = KenyaGroup.class
        )
        @Pattern(
            regexp = "N|Y|G|P|L|M|B|D|I|O|T|S|F|U|A|C|R",
            message = "ageProof must be one of N,Y,G,P,L,M,B,D,I,O,T,S,F,U,A,C,R",
            groups = LankaGroup.class
        )
        @Size(max = 3)
        String age_proof,

        @NotBlank(message = "Education is mandatory")
        @Pattern(
            regexp = "G|S|H|L|I",
            message = "Education must be G,S,H,L or I"
        )
        @Size(max = 1)
        String education,

        @NotBlank(message = "Occupation is mandatory")
        @Pattern(
            regexp = "10|20|30|40|50",
            message = "Occupation must be one of 10,20,30,40,50",
            groups = KenyaGroup.class
        )
        @Pattern(
            regexp = "(2[0-7])|(3[0-8])|(4[1-9])|50|(6[0-9])|(7[1-8])",
            message = "Invalid occupation code",
            groups = LankaGroup.class
        )
        @Size(max = 2)
        String occupation,

        @NotBlank(message = "Customer ID is mandatory")
        @Pattern(regexp = "\\d{1,9}", message = "Customer ID must be numeric")
        String customer_id,

        @NotBlank(message = "Father name is mandatory")
        @Size(max = 20)
        String father_name,

        @NotBlank(message = "National ID is mandatory")
        @Size(max = 20)
        String national_id,

        @NotBlank(message = "Nationality is mandatory")
        @Size(max = 20)
        String nationality,

        @NotBlank(message = "Employer name is mandatory")
        @Size(max = 40)
        String employer_name,

        String marital_status,

        @NotBlank(message = "Place of birth is mandatory")
        @Size(max = 60)
        String place_of_birth,

        @Valid
        PassportDetails passport_details,

        String length_of_service,

        @NotBlank(message = "Proposer full name is mandatory")
        @Size(max = 75)
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

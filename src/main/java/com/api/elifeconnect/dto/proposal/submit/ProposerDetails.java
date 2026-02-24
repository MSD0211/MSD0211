
package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.Valid;
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
        @Size(max = 150)
        String email_id,

        @NotBlank(message = "Age proof is mandatory")
        @Pattern(
            regexp = "M|S|D|E|R|P|C|L|N",
            message = "Age proof must be one of M,S,D,E,R,P,C,L,N"
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
            regexp = "10|60|63|70",
            message = "Occupation must be one of 10,60,63,70"
        )
        @Size(max = 60)
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

        @NotBlank(message = "Place of birth is mandatory")
        @Size(max = 60)
        String place_of_birth,

        @Valid
        PassportDetails passport_details,

        @NotBlank(message = "Proposer full name is mandatory")
        @Size(max = 75)
        String proposer_full_name,

        @Valid
        AddressOfCommunication address_of_communication
) {}

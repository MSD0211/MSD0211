package com.api.elifeconnect.dto.proposal.deposit;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.*;

public record ProposalDepositCreateRequest(

    @NotBlank(message = "ID is mandatory")
    @Size(max = 30, message = "ID must not exceed 30 characters")
    @JsonAlias({ "referenceId" })
    String id,

    @NotBlank(message = "Agency code is mandatory")
    @Size(max = 9, message = "ID must not exceed 9 characters")
    String agencyCode,

    @NotBlank(message = "Customer name is mandatory")
    @Pattern(
        regexp = "[A-Za-z ]{3,75}",
        message = "Customer name must contain only letters and spaces (3–75 characters)"
    )
    String customerName,

    @NotBlank(message = "National ID is mandatory")
    @Size(max = 20, message = "National ID must not exceed 20 characters")
    @JsonAlias({ "nationalId","uid" })
    String nid,

    @NotBlank(message = "Mobile number is mandatory")
    @Size(max = 20, message = "Mobile Number must not exceed 20 characters")
    @JsonAlias({ "mobileNo", "mobile_number", "mobile" })
    String mobileNo,

    @NotBlank(message = "Date of birth is mandatory")
    @Pattern(
        regexp = "\\d{4}-\\d{2}-\\d{2}",
        message = "Date of birth must be in yyyy-MM-dd format"
    )
    String dob,

    @NotBlank(message = "Collection amount is mandatory")
    @Pattern(
        regexp = "^(0*[1-9]\\d*(\\.\\d{1,2})?)$",
        message = "Collection amount must be a positive number with up to 2 decimal places"
    )
    String collectionAmount,

    @NotBlank(message = "Collection date is mandatory")
    @Pattern(
        regexp = "\\d{4}-\\d{2}-\\d{2}",
        message = "Collection date must be in yyyy-MM-dd format"
    )
    String collectionDate
) {
}
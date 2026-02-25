package com.api.elifeconnect.dto.proposal.deposit;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProposalDepositCreationRequest(

        @NotBlank(message = "Reference ID is required")
        @JsonAlias({ "referenceId" })
        String id,

        @NotBlank(message = "Agency code is required")
        @JsonAlias({ "agencyCode" })
        String agencyCode,

        @NotBlank(message = "Customer name is required")
        @JsonAlias({ "customerName" })
        String customerName,

        @NotBlank(message = "National Id is required")
        @JsonAlias({ "nationalId","uid" })
        String nid,

        @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
        @JsonAlias({ "mobileNo", "mobile_number", "mobile" })
        String mobileNo,

        @Past(message = "Date of birth must be in the past")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @JsonAlias({ "dob", "dateOfBirth" })
        LocalDate dob,

        @NotNull(message = "Collection amount is required")
        @DecimalMin(value = "1.0", message = "Amount must be greater than zero")
        @JsonAlias({ "collectionAmount", "amount" })
        BigDecimal collectionAmount,

        @NotNull(message = "Collection date is required")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @JsonAlias({ "collectionDate", "paidDate" })
        LocalDate collectionDate
) {}

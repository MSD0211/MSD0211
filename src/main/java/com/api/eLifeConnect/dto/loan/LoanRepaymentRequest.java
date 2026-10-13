package com.api.elifeconnect.dto.loan;

import jakarta.validation.constraints.*;

public record LoanRepaymentRequest(
        @NotBlank
        String enqReferenceNo,
        @NotBlank
        @Size(max = 9)
        String policyNo,
        @NotBlank
        String amount,
        @NotBlank
        String collDate,
        @NotBlank
        @Size(max = 30)
        String referenceNo
        ) {}

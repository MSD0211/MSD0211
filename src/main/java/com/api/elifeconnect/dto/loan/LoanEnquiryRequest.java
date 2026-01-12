package com.api.elifeconnect.dto.loan;

import jakarta.validation.constraints.*;

public record LoanEnquiryRequest(
        @NotBlank String referenceNo,
        @NotNull String policyNumber
) {}

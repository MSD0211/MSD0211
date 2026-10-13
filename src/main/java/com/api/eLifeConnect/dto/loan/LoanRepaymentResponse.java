package com.api.elifeconnect.dto.loan;

public record LoanRepaymentResponse(
        String policyNo, String referenceNo, String httpStatus,
        String message, String loanAmtAdjusted, String loanIntAdjusted, String timestamp
) {}
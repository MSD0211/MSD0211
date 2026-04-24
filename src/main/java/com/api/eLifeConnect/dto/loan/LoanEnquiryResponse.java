package com.api.elifeconnect.dto.loan;


public record LoanEnquiryResponse(
        String name,
        String policyNumber,
        String loanAmount,
        String interestAmount,
        String totalLoanAmount,
        String httpStatus
) {}

package com.api.elifeconnect.dto.loan;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response for  loan enquiry")
public record LoanEnquiryResponse(
        @Schema(description = "Policyholder name", example = "DK KHATRI")
        String name,
        @Schema(description = "Policy number", example = "240007757")
        String policyNumber,
        @Schema(description = "Outstanding loan principal", example = "100019.0")
        String loanAmount,
        @Schema(description = "Interest due up to date", example = "8007.0")
        String interestAmount,
        @Schema(description = "Total amount payable (principal + interest)", example = "108026.0")
        String totalLoanAmount,
        @Schema(description = "Not populated by eLife (status conveyed via HTTP code); retained for compatibility", example = "200")
        String httpStatus,
        @Schema(description = "Loan interest from date", example = "2026-01-15")
        String fromDue,
        @Schema(description = "Interest paid up to date", example = "2026-10-29")
        String toDue
        ) {

}

package com.api.elifeconnect.dto.loan;

import jakarta.validation.constraints.*;

public record LoanRepaymentLetterRequest(
        @NotBlank String referenceId,
        @NotNull String policyNumber
) {}

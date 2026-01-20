package com.api.elifeconnect.dto.loan;

import jakarta.validation.constraints.*;

public record LoanQuotationRequest(
        @NotBlank String referenceId,
        @NotNull String policyNumber
) {}

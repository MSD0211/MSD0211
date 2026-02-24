package com.api.elifeconnect.dto.loan;

import jakarta.validation.constraints.*;

public record LoanRepaymentLetterRequest(

        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        String referenceId,

        @NotBlank(message = "policyNumber is required")
        @Size(max = 9) 
        @NotNull String policyNumber
) {}

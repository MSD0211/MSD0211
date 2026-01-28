package com.api.elifeconnect.dto.ulip.fund;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record UlipFundPositionSingleResponse(

        @NotBlank(message = "Policy number must not be blank")
        String policyNumber,

        @NotNull(message = "Plan is required")
        Integer plan,

        @NotNull(message = "Sum assured is required")
        @Positive(message = "Sum assured must be positive")
        BigDecimal sumAssured,

        @NotNull(message = "Invested amount is required")
        @Positive(message = "Invested amount must be positive")
        BigDecimal investedAmount,

        @NotNull(message = "Target amount is required")
        @Positive(message = "Target amount must be positive")
        BigDecimal targetAmount,

        @NotNull(message = "Date of commencement is required")
        LocalDate doc,

        @NotBlank(message = "Policy type is required")
        String type,

        @NotNull(message = "Fund as on date is required")
        LocalDate fundAsOn,

        @NotNull(message = "Fund value is required")
        @PositiveOrZero(message = "Fund value cannot be negative")
        BigDecimal fundValue,

        @NotBlank(message = "Name must not be blank")
        String name,

        @NotBlank(message = "Address line 1 must not be blank")
        String addressLine1,

        @NotBlank(message = "Address line 2 must not be blank")
        String addressLine2,

        @NotNull(message = "Pin code is required")
        @Min(value = 100000, message = "Pin code must be 6 digits")
        @Max(value = 999999, message = "Pin code must be 6 digits")
        Integer pinCode,

        @NotEmpty(message = "Transaction details cannot be empty")
        @Valid
        List<TransactionDetails> transactionDetails
) {
}

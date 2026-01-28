package com.api.elifeconnect.dto.ulip.fund;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.*;

public record TransactionDetails(

        @NotNull(message = "Transaction date is required")
        LocalDate transactionDate,

        @NotNull(message = "Financial year is required")
        @Min(value = 1900, message = "Invalid financial year")
        Integer financialYear,

        @NotNull(message = "Interest rate is required")
        @PositiveOrZero(message = "Interest rate cannot be negative")
        BigDecimal interestRate,

        @NotNull(message = "Opening balance is required")
        @PositiveOrZero(message = "Opening balance cannot be negative")
        BigDecimal openingBalance,

        @NotNull(message = "Single premium / top-up amount is required")
        @PositiveOrZero(message = "Single premium cannot be negative")
        BigDecimal singlePremiumRenewalTopUp,

        @NotNull(message = "Withdrawal amount is required")
        @PositiveOrZero(message = "Withdrawal cannot be negative")
        BigDecimal withDrawal,

        @NotNull(message = "Interest earned is required")
        @PositiveOrZero(message = "Interest earned cannot be negative")
        BigDecimal interestEarn,

        @NotNull(message = "Total deductions is required")
        @PositiveOrZero(message = "Total deductions cannot be negative")
        BigDecimal totalDeductions,

        @NotNull(message = "Closing balance is required")
        @PositiveOrZero(message = "Closing balance cannot be negative")
        BigDecimal closingBalance,

        @NotNull(message = "LA age is required")
        @Min(value = 0, message = "LA age cannot be negative")
        Integer laAge,

        @NotNull(message = "Death sum assured is required")
        @PositiveOrZero(message = "Death sum assured cannot be negative")
        BigDecimal deathSumAssured
) {
}

package com.api.elifeconnect.dto.premium.statement;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PremiumPaymentDetails(

        @NotNull
        Integer receiptNumber,

        @NotNull
        LocalDate date,

        @NotNull
        BigDecimal amount,

        String transactionStatus,

        @NotNull
        LocalDate fromDue,

        @NotNull
        LocalDate toDue,

        @NotNull @NotBlank
        String received,

        @NotNull @NotBlank
        String towards,

        @NotNull
        Integer noOfInstallment
) {
}

package com.api.elifeconnect.dto.premium.statement;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PremiumStatementFullResponse(

        @NotNull @NotBlank
        String policyNumber,

        @NotNull @NotBlank
        String name,

        @NotNull
        @Valid
        AddressDetails addressDetails,

        @NotNull
        Integer plan,

        @NotNull @NotBlank
        String planName,

        @NotNull
        Integer term,

        @NotNull
        LocalDate doc,   // Date of Commencement

        @NotNull
        LocalDate dom,   // Date of Maturity

        @NotNull @NotBlank
        String sumAssured,

        @NotNull
        LocalDate premDueDate,

        @NotNull @NotBlank
        String mode,

        @NotNull
        LocalDate fup,

        @NotNull @NotBlank
        String firstPremium,

        @NotNull
        BigDecimal totalRegularPremium,

        @NotNull
        BigDecimal totalTopUpPremium,

        @NotNull
        BigDecimal totalPremium,

        @NotNull
        @Valid
        List<PremiumPaymentDetails> premiumPaymentDetails,

        String message

        //String httpStatus

) {
}

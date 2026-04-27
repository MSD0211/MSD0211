package com.api.elifeconnect.dto.premium.summary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PremiumSummaryResponse(

        @NotNull @NotBlank
        String policyNumber,

        @NotNull @NotBlank
        String name,

        @NotNull
        AddressDetails addressDetails,

        @NotNull
        Integer plan,

        @NotNull @NotBlank
        String planName,

        @NotNull
        Integer term,

        @NotNull
        LocalDate doc,

        @NotNull
        LocalDate dom,

        @NotNull
        LocalDate due,

        @NotNull @NotBlank
        String mode,

        @NotNull @NotBlank
        String sumAssured,

        @NotNull
        BigDecimal premium,

        @NotNull @NotBlank
        String noOfPremiumsPaid,

        @NotNull @NotBlank
        String totalPremiumAmountPaid
) {
}

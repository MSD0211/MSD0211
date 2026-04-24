package com.api.elifeconnect.dto.policy;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PolicyRevivalQuotationResponse(

        @JsonProperty("policyNumber")
        @NotBlank String policyNumber,

        @NotBlank String name,

        @NotBlank String addressLine1,

        String addressLine2,

        @NotBlank String pinCode,

        @NotBlank String fromDue,

        @NotBlank String toDue,

        @NotBlank String noOfDues,

        @NotBlank String premiumMode,

        @NotNull BigDecimal sumAssured,

        @NotNull BigDecimal installmentPremium,

        @NotNull BigDecimal totalPremiumOutstanding,

        @NotNull BigDecimal lateFee,

        @NotNull BigDecimal phcf,

        @NotNull BigDecimal grossRevivalAmount,

        @NotNull BigDecimal depositAvailable,

        @NotNull BigDecimal interestOnDeposit,

        @NotNull BigDecimal balanceRequiredAmount,

        @NotBlank String quotationValidUpto,

        @NotBlank String durationOfPremiumPaid,

        @NotNull BigDecimal sbCumRevivalOrRevivalAmount

) {
}

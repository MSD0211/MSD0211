package com.api.elifeconnect.dto.proposal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProposalPremiumEnquiryResponse(

        @NotBlank String proposalNumber,

        @NotNull BigDecimal installmentPremium

) {
}

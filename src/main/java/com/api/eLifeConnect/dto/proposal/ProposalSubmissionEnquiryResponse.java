package com.api.elifeconnect.dto.proposal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProposalSubmissionEnquiryResponse(

        @NotBlank String submittedOn,

        @NotBlank String proposalRegisteredOn,

        @NotBlank String proposalNumber,

        @NotBlank String underwrittingDecision,

        @NotBlank String policyNumber,

        @NotNull BigDecimal installmentPremium,

        @NotBlank String proposalStatus

) {
}

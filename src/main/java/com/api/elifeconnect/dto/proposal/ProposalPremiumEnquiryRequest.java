package com.api.elifeconnect.dto.proposal;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProposalPremiumEnquiryRequest(

        @NotBlank(message="Proposal Number is required")
        @Size(max = 9)
        String proposalNumber,
        
        @JsonAlias({"referenceId"})
        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        @NotBlank String referenceNumber

) {
}

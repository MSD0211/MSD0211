package com.api.elifeconnect.dto.proposal;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;

public record ProposalPremiumEnquiryRequest(

        @NotBlank String proposalNumber,
        
        @JsonAlias({"referenceId"})
        @NotBlank String referenceNumber

) {
}

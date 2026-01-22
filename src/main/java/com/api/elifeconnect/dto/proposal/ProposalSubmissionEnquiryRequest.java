package com.api.elifeconnect.dto.proposal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record ProposalSubmissionEnquiryRequest(
        
        @JsonAlias({"referenceId"})
        @NotBlank String referenceNumber,
        @JsonAlias({"originalReferenceId"})
        @NotBlank String originalReferenceNumber
) {
}

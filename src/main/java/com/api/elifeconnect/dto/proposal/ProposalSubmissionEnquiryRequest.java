package com.api.elifeconnect.dto.proposal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProposalSubmissionEnquiryRequest(
        
        @JsonAlias({"referenceId"})
        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        String referenceNumber,
        
        @JsonAlias({"originalReferenceId"})
        @NotBlank(message = "originalReferenceId is required")
        @Size(max = 30)
        String originalReferenceNumber
) {
}

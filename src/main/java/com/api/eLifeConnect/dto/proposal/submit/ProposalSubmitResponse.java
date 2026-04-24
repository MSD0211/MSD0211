package com.api.elifeconnect.dto.proposal.submit;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;

public record ProposalSubmitResponse(
        @NotBlank String code,
        @NotBlank String message,
        @JsonIgnore
        List<String> errors,
        @JsonIgnore
        Map<String, Boolean> extractResults,
        @JsonIgnore
        Map<String, String> mergeResults
) {}

package com.api.elifeconnect.dto.proposal.submit;

import java.util.List;
import java.util.Map;
import jakarta.validation.constraints.NotBlank;

public record ProposalSubmitResponse(
        @NotBlank String code,
        @NotBlank String message,
        List<String> errors,
        Map<String, Boolean> extractResults,
        Map<String, String> mergeResults
) {}

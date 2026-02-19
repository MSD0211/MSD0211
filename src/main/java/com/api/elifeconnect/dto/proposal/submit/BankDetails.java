package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;

public record BankDetails(
        @NotBlank(message = "bank name is required")
        String name,

        @NotBlank(message = "branch is required")
        String branch,

        @NotBlank(message = "account_number is required")
        String account_number,

        String routing_number
) {}

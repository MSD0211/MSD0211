package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;

public record BocDetails(
        @NotBlank(message = "boc_no is required")
        String boc_no,

        @NotBlank(message = "boc_date is required")
        String boc_date,

        @NotBlank(message = "boc_amount is required")
        String boc_amount
) {}

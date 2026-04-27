package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record BocDetails(
        @NotBlank(message = "BOC number is mandatory")
        @Pattern(regexp = "\\d+", message = "BOC number must be numeric")
        String boc_no,

        @NotBlank(message = "BOC date is mandatory")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "BOC date must be in YYYY-MM-DD format")
        String boc_date,

        @NotBlank(message = "BOC amount is mandatory")
        @Pattern(regexp = "\\d{1,12}(\\.\\d{1,2})?", message = "BOC amount must be decimal(12,2)")
        String boc_amount
) {}

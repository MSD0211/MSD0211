package com.api.elifeconnect.dto.gp.scheme;

import jakarta.validation.constraints.*;

public record SchemeFundBalanceRequest(

    @NotBlank(message = "Reference ID is required")
    String referenceId,

    @NotBlank(message = "Scheme Number is required")
    String schemeNumber,

    @NotBlank(message = "Member Status is required")
    String memberStatus,

    @NotNull(message = "Year is required")
    @Min(value = 1900, message = "Year must be valid")
    @Max(value = 2100, message = "Year must be valid")
    Integer year,

    @NotBlank(message = "Report Type is required")
    String reportType,

    @NotBlank(message = "Trustee ID is required")
    String trusteeId

) {}
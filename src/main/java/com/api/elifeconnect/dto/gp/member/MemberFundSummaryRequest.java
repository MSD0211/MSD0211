package com.api.elifeconnect.dto.gp.member;

import jakarta.validation.constraints.*;

public record MemberFundSummaryRequest(

    @NotBlank(message = "Reference ID is required")
    String referenceId,

    @NotBlank(message = "Scheme Number is required")
    String schemeNumber,

    @Min(value = 1, message = "Member ID must be greater than 0")
    int memberId,

    @Min(value = 1900, message = "Year must be valid")
    @Max(value = 2100, message = "Year must be valid")
    int year

) {}

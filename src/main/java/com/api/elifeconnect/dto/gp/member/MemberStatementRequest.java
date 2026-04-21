
package com.api.elifeconnect.dto.gp.member;

import jakarta.validation.constraints.*;

public record MemberStatementRequest(

    @NotBlank(message = "referenceId is required")
    @Size(max = 30)
    String referenceId,

    @NotBlank(message = "schemeNumber is required")
    @Size(max = 20, message = "schemeNumber must be at most 20 digits")
    String schemeNumber,

    @Min(value = 1, message = "memberId must be greater than 0")
    int memberId,

    @Min(value = 2000, message = "year must be 2000 or later")
    @Max(value = 2100, message = "year must be 2100 or earlier")
    int year

) {}
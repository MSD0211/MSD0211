package com.api.elifeconnect.dto.gp.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record MemberDetailsRequest(

        @NotBlank(message = "Reference ID is required")
        String referenceId,

        @NotBlank(message = "Scheme number is required")
        String schemeNumber,

        @Positive(message = "Member ID must be a positive number")
        int memberId

) {}
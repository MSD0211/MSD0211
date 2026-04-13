package com.api.elifeconnect.dto.gp.member;

import jakarta.validation.constraints.*;

public record MemberStatement(
        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        String referenceNo,
        
        @NotBlank(message = "policyNumber is required")
        @Size(max = 9) 
        String policyNumber
) {}

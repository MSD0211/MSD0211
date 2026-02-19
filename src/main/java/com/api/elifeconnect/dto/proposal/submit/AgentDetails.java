package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AgentDetails(
        @NotBlank(message = "agent code is required")
        String code,

        @NotBlank(message = "agent name is required")
        String name,

        String type,

        @Email(message = "invalid agent email")
        String email,

        @NotBlank(message = "mobile number is required")
        String mobile_no
) {}

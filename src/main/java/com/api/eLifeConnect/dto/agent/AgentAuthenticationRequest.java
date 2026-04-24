package com.api.elifeconnect.dto.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AgentAuthenticationRequest(

        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        String referenceId,

        @NotBlank(message = "agentId is required")
        @Size(max = 9)
        String agentId

) {}
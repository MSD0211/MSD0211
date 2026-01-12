package com.api.elifeconnect.dto.agent;

import jakarta.validation.constraints.*;

public record AgentAuthenticationRequest(
        @NotBlank String referenceId,
        @NotNull String agentId
) {}
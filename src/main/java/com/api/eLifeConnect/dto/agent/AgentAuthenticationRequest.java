package com.api.elifeconnect.dto.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for agent authentication")
public record AgentAuthenticationRequest(

        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        @Schema(description = "Unique reference ID for tracing the request", example = "REF123456789")
        String referenceId,

        @NotBlank(message = "agentId is required")
        @Size(max = 9)
        @Schema(description = "Unique identifier of the agent", example = "AGT007")
        String agentId

) {}
package com.api.elifeconnect.dto.policy;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for agent policy enquiry")
public record AgentPolicyEnquiryRequest(

        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        @Schema(description = "Unique reference ID for request tracing", example = "REF-20261016-002")
        String referenceId,
        
        @NotBlank(message = "agentId is required")
        @Size(max = 9)
        @Schema(description = "Agent ID", example = "AGT001234")
        String agentId
) {
}
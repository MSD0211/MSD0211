package com.api.elifeconnect.dto.agent;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response object for agent authentication")
public record AgentAuthenticationResponse(
        @Schema(description = "Response status message", example = "Agent Authentication done")
        String message,

        @Schema(description = "Name of the agent", example = "John Doe")
        String name,

        @Schema(description = "Mobile number of the agent", example = "712345678")
        Long mobileno,

        @JsonProperty("email_id")
        @Schema(description = "Email ID of the agent", example = "agent@elife.com")
        String emailId,

        @JsonProperty("agency_status_code")
        @Schema(description = "Agency status code", example = "1")
        Integer agencyStatusCode,

        @JsonProperty("status_code_description")
        @Schema(description = "Status code description", example = "Active")
        String statusCodeDescription
) {}

package com.api.elifeconnect.dto.agent;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AgentAuthenticationResponse(
        String message,
        String name,
        Long mobileno,

        @JsonProperty("email_id")
        String emailId,

        @JsonProperty("agency_status_code")
        Integer agencyStatusCode,

        @JsonProperty("status_code_description")
        String statusCodeDescription
) {}

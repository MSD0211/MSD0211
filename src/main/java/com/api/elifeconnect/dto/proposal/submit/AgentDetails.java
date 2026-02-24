package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AgentDetails(

        @NotBlank(message = "Agent code is mandatory")
        @Size(max = 9)
        String code,

        @NotNull(message = "Agent name cannot be null !!")
        @Size(max = 40)
        String name,

        @NotNull(message = "Agent type cannot be null !!")
        @Size(max = 2)
        String type,

        @NotBlank(message = "Agent email is mandatory")
        @Email(message = "Invalid email format")
        @Size(max = 150)
        String email,

        @NotBlank(message = "Agent mobile number is mandatory")
        @Size(max = 20)
        @Pattern(regexp = "\\d+", message = "Mobile number must be numeric")
        String mobile_no
) {}

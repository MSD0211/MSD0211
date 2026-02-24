package com.api.elifeconnect.dto.customer;

import jakarta.validation.constraints.*;

public record CustomerAuthenticationRequest(

        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        String referenceId,
        
        @NotBlank(message = "clientId is required")
        @Size(max = 9)
        String clientId
) {}
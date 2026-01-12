package com.api.elifeconnect.dto.customer;

import jakarta.validation.constraints.*;

public record CustomerAuthenticationRequest(
        @NotBlank String referenceId,
        @NotNull String clientId
) {}
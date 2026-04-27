package com.api.elifeconnect.dto.customer;

import jakarta.validation.constraints.*;

public record CustomerAuthenticationResponse(
        String status,
        String message,
        String mobileNo,
        String emailId
) {}
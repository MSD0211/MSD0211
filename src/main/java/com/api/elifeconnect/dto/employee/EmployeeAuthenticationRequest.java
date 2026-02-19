package com.api.elifeconnect.dto.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;

public record EmployeeAuthenticationRequest(
        @NotBlank(message = "referenceId is required")
        String referenceId,

        @NotBlank(message = "employeeId is required")
        String employeeId,

        @JsonIgnore
        String password
) {}

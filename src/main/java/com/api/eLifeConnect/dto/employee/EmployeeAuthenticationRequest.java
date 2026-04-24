package com.api.elifeconnect.dto.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeAuthenticationRequest(
        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        String referenceId,

        @NotBlank(message = "employeeId is required")
        @Size(max = 9)
        String employeeId,

        @JsonIgnore
        String password
) {}

package com.api.elifeconnect.dto.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record EmployeeAuthenticationRequest(
        String referenceId,
        String employeeId,
        @JsonIgnore
        String password
) {}

package com.api.elifeconnect.dto.employee;

public record EmployeeAuthenticationRequest(
        String referenceId,
        String employeeId,
        String password
) {}

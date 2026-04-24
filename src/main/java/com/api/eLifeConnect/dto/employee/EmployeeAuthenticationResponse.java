package com.api.elifeconnect.dto.employee;

public record EmployeeAuthenticationResponse(
        boolean success,
        String message,
        String name,
        long mobile,
        String emailId
) {}

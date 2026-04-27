package com.api.elifeconnect.service.employee;

import com.api.elifeconnect.dto.employee.EmployeeAuthenticationRequest;
import com.api.elifeconnect.dto.employee.EmployeeAuthenticationResponse;

public interface EmployeeAuthenticationService {

    EmployeeAuthenticationResponse employeeAuthentication(EmployeeAuthenticationRequest req);

}

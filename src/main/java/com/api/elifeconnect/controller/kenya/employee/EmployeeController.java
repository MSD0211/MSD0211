package com.api.elifeconnect.controller.kenya.employee;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.employee.EmployeeAuthenticationRequest;
import com.api.elifeconnect.dto.employee.EmployeeAuthenticationResponse;
import com.api.elifeconnect.service.employee.EmployeeAuthenticationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/kenya/employee")
public class EmployeeController {

    private final EmployeeAuthenticationService custAuthService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public EmployeeController(EmployeeAuthenticationService custAuthService) {
        this.custAuthService = custAuthService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/authentication")
    // @PreAuthorize("hasAnyRole('USER')")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("Employee Authentication API")
    public ResponseEntity<ApiResponse<EmployeeAuthenticationResponse>> employeeAuthentication(
            @RequestBody EmployeeAuthenticationRequest empAuthenticationRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "Employee Authentication API");
        EmployeeAuthenticationResponse response = custAuthService.employeeAuthentication(empAuthenticationRequest);
        System.out.println("RESPONSE ::"+response.message());
        System.out.println("RESPONSE::"+response.emailId());
        ApiResponse<EmployeeAuthenticationResponse> body =
                responseBuilder.success(request, "Employee Authentication done", response);

        return ResponseEntity.ok(body);
    }

}

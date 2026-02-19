package com.api.elifeconnect.controller.kenya.customer;

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
import com.api.elifeconnect.dto.customer.CustomerAuthenticationRequest;
import com.api.elifeconnect.dto.customer.CustomerAuthenticationResponse;
import com.api.elifeconnect.service.customer.CustomerAuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/kenya/customer")
public class CustomerController {

    private final CustomerAuthenticationService custAuthService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public CustomerController(CustomerAuthenticationService custAuthService) {
        this.custAuthService = custAuthService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/authentication")
    // @PreAuthorize("hasAnyRole('USER')")
    @PreAuthorize("hasAuthority('kenya.kenya.api.read')")
    @LogApiCall("Customer Authentication API")
    public ResponseEntity<ApiResponse<CustomerAuthenticationResponse>> customerAuthentication(
            @Valid @RequestBody CustomerAuthenticationRequest custAuthenticationRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "Customer Authentication API");
        CustomerAuthenticationResponse response = custAuthService.customerAuthentication(custAuthenticationRequest);
        System.out.println("RESPONSE ::"+response.message());
        System.out.println("RESPONSE::"+response.mobileNo());
        ApiResponse<CustomerAuthenticationResponse> body =
                responseBuilder.success(request, "Customer Authentication done", response);

        return ResponseEntity.ok(body);
    }

}

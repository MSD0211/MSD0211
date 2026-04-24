package com.api.elifeconnect.service.employee.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.employee.EmployeeAuthenticationRequest;
import com.api.elifeconnect.dto.employee.EmployeeAuthenticationResponse;
import com.api.elifeconnect.service.employee.EmployeeAuthenticationService;

@Service
public class EmployeeAuthenticationServiceImpl implements EmployeeAuthenticationService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;
    
    @Value("${elife.api.employee.authentication.url}")
    private String eLifeApiEmployeeAuthenticationUrl;

    public EmployeeAuthenticationServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public EmployeeAuthenticationResponse employeeAuthentication(EmployeeAuthenticationRequest req) {
        String url = eLifeApiBaseUrl + eLifeApiEmployeeAuthenticationUrl;
        System.out.println("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), EmployeeAuthenticationResponse.class)
                     .block();   // convert Mono → object
    }
}


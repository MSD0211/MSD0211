package com.api.elifeconnect.service.customer.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.customer.CustomerAuthenticationRequest;
import com.api.elifeconnect.dto.customer.CustomerAuthenticationResponse;
import com.api.elifeconnect.service.customer.CustomerAuthenticationService;

@Service
public class CustomerAuthenticationServiceImpl implements CustomerAuthenticationService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;
    
    @Value("${elife.api.customer.authentication.url}")
    private String eLifeApiCustomerAuthenticationUrl;

    public CustomerAuthenticationServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public CustomerAuthenticationResponse customerAuthentication(CustomerAuthenticationRequest req) {
        String url = eLifeApiBaseUrl + eLifeApiCustomerAuthenticationUrl;
        System.out.println("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), CustomerAuthenticationResponse.class)
                     .block();   // convert Mono → object
    }
}


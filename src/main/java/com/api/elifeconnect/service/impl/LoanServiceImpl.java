package com.api.elifeconnect.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.loan.LoanEnquiryRequest;
import com.api.elifeconnect.dto.loan.LoanEnquiryResponse;
import com.api.elifeconnect.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;
    
    @Value("${elife.api.loan.enquiry.url}")
    private String eLifeApiLoanEnquiryUrl;

    public LoanServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public LoanEnquiryResponse loanEnquiry(LoanEnquiryRequest req) {
        String url = eLifeApiBaseUrl + eLifeApiLoanEnquiryUrl;
        System.out.println("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), LoanEnquiryResponse.class)
                     .block();   // convert Mono → object
    }
}


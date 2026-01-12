package com.api.elifeconnect.service.premium.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentResponse;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryResponse;
import com.api.elifeconnect.service.premium.PremiumService;

@Service
public class PremiumServiceImpl implements PremiumService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;
    
    @Value("${elife.api.premium.renewal.enquiry.url}")
    private String eLifeApiRenewalPremiumEnquiryUrl;

    @Value("${elife.api.premium.renewal.adjustment.url}")
    private String eLifeApiRenewalPremiumAdjustmentUrl;

    public PremiumServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public RenewalPremiumEnquiryResponse renewalPremiumEnquiry(RenewalPremiumEnquiryRequest req) {
        String url = eLifeApiBaseUrl + eLifeApiRenewalPremiumEnquiryUrl;
        System.out.println("REQUEST REFERENCE ID::"+req.policyNo());
        System.out.println("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), RenewalPremiumEnquiryResponse.class)
                     .block();   // convert Mono → object
    }
   
    @Override
    public RenewalPremiumAdjustmentResponse renewalPremiumAdjustment(RenewalPremiumAdjustmentRequest req){
        String url = eLifeApiBaseUrl + eLifeApiRenewalPremiumAdjustmentUrl;
        System.out.println("REQUEST REFERENCE ID::"+req.policyNo());
        System.out.println("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), RenewalPremiumAdjustmentResponse.class)
                     .block();   // convert Mono → object
    }
}


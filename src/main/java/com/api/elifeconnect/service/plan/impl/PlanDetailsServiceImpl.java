package com.api.elifeconnect.service.plan.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.plan.Details.PlanDetailsRequest;
import com.api.elifeconnect.dto.plan.Details.PlanDetailsResponse;
import com.api.elifeconnect.service.plan.PlanDetailsService;

@Service
public class PlanDetailsServiceImpl implements PlanDetailsService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;
    
    @Value("${elife.api.plan.details.url}")
    private String eLifeApiPlanDetailsUrl;

    public PlanDetailsServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public PlanDetailsResponse getPlanDetails(PlanDetailsRequest req) {
        String url = eLifeApiBaseUrl + eLifeApiPlanDetailsUrl;
        System.out.println("REQUEST REFERENCE ID::"+req.referenceId());
        System.out.println("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), PlanDetailsResponse.class)
                     .block();   // convert Mono → object
    }
}


package com.api.elifeconnect.service.policy.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.policy.AgentPolicyEnquiryRequest;
import com.api.elifeconnect.dto.policy.AgentPolicyEnquiryResponse;
import com.api.elifeconnect.dto.policy.CustomerPolicyEnquiryRequest;
import com.api.elifeconnect.dto.policy.CustomerPolicyEnquiryResponse;
import com.api.elifeconnect.dto.policy.PolicyDetails;
import com.api.elifeconnect.service.policy.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

    @Value("${elife.api.policy.customer.enquiry.url}")
    private String eLifeApiCustomerPolicyEnquiryUrl;

    @Value("${elife.api.policy.agent.enquiry.url}")
    private String eLifeApiAgentPolicyEnquiryUrl;

    public PolicyServiceImpl(WebClientUtil client) {
        this.client = client;
    }


    @Override
    public CustomerPolicyEnquiryResponse customerPolicyEnquiry(CustomerPolicyEnquiryRequest req){
        String url = eLifeApiBaseUrl + eLifeApiCustomerPolicyEnquiryUrl;
        System.out.println("REQUEST REFERENCE ID::" + req.referenceId());
        System.out.println("URL::" + url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), CustomerPolicyEnquiryResponse.class)
                .block(); // convert Mono → object
    }

    @Override
    public AgentPolicyEnquiryResponse agentPolicyEnquiry(AgentPolicyEnquiryRequest req){
        String url = eLifeApiBaseUrl + eLifeApiAgentPolicyEnquiryUrl;
        System.out.println("REQUEST REFERENCE ID::" + req.referenceId());
        System.out.println("URL::" + url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), AgentPolicyEnquiryResponse.class)
                .block(); // convert Mono → object
    }

}

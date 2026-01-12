package com.api.elifeconnect.service.agent.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.agent.AgentAuthenticationRequest;
import com.api.elifeconnect.dto.agent.AgentAuthenticationResponse;
import com.api.elifeconnect.service.agent.AgentAuthenticationService;

@Service
public class AgentAuthenticationServiceImpl implements AgentAuthenticationService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;
    
    @Value("${elife.api.agent.authentication.url}")
    private String eLifeApiAgentAuthenticationUrl;

    public AgentAuthenticationServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public AgentAuthenticationResponse agentAuthentication(AgentAuthenticationRequest req) {
        String url = eLifeApiBaseUrl + eLifeApiAgentAuthenticationUrl;
        System.out.println("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), AgentAuthenticationResponse.class)
                     .block();   // convert Mono → object
    }
}


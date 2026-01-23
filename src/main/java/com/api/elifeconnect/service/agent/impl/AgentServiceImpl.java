package com.api.elifeconnect.service.agent.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.agent.AgentAuthenticationRequest;
import com.api.elifeconnect.dto.agent.AgentAuthenticationResponse;
import com.api.elifeconnect.dto.agent.CommissionStatementRequest;
import com.api.elifeconnect.service.agent.AgentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AgentServiceImpl implements AgentService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;
    
    @Value("${elife.api.agent.authentication.url}")
    private String eLifeApiAgentAuthenticationUrl;

    @Value("${elife.api.agent.commission.statement.url}")
    private String eLifeApiAgentCommissionStatementUrl;

    public AgentServiceImpl(WebClientUtil client) {
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

    @Override
    public byte[] generateCommissionStatement(CommissionStatementRequest commissionStatementRequest){
        String url = eLifeApiBaseUrl + eLifeApiAgentCommissionStatementUrl;
        log.error("URL::"+url);

        return client.downloadPdf(url, commissionStatementRequest, Map.of()).block();
    }
}


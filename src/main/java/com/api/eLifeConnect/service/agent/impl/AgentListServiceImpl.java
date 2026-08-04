package com.api.elifeconnect.service.agent.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.agent.DueListRequest;
import com.api.elifeconnect.dto.agent.DueListResponse;
import com.api.elifeconnect.dto.agent.LapseListRequest;
import com.api.elifeconnect.dto.agent.LapseListResponse;
import com.api.elifeconnect.service.agent.AgentListService;

@Service
public class AgentListServiceImpl implements AgentListService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

    @Value("${elife.api.agent.duelist.url}")
    private String eLifeApiDueListUrl;

    @Value("${elife.api.agent.lapselist.url}")
    private String eLifeApiLapseListUrl;

    public AgentListServiceImpl(WebClientUtil client) {
        this.client = client;
    }


    @Override
    public DueListResponse fetchDueList(DueListRequest req){
        String url = eLifeApiBaseUrl + eLifeApiDueListUrl;
        System.out.println("REQUEST REFERENCE ID::" + req.referenceId());
        System.out.println("URL::" + url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), DueListResponse.class)
                .block(); // convert Mono → object
    }

    @Override
    public LapseListResponse fetchLapseList(LapseListRequest req){
        String url = eLifeApiBaseUrl + eLifeApiLapseListUrl;
        System.out.println("REQUEST REFERENCE ID::" + req.referenceId());
        System.out.println("URL::" + url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), LapseListResponse.class)
                .block();
    }

}

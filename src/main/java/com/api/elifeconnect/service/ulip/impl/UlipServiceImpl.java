package com.api.elifeconnect.service.ulip.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.ulip.fund.UlipFundPositionSingleRequest;
import com.api.elifeconnect.dto.ulip.fund.UlipFundPositionSingleResponse;
import com.api.elifeconnect.service.ulip.UlipService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UlipServiceImpl implements UlipService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;
    
    @Value("${elife.api.ulip.fund.position.single.url}")
    private String eLifeUlipFundPositionSingleUrl;


    public UlipServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public UlipFundPositionSingleResponse getFundPositionSingle(UlipFundPositionSingleRequest req){
        String url = eLifeApiBaseUrl + eLifeUlipFundPositionSingleUrl;
        log.error("REQUEST REFERENCE ID::"+req.referenceId());
        log.error("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), UlipFundPositionSingleResponse.class)
                     .block();   // convert Mono → object
    }

}


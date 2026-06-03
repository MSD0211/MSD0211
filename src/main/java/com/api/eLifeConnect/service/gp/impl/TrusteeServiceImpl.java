package com.api.elifeconnect.service.gp.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.dto.gp.trustee.TrusteeDetailsRequest;
import com.api.elifeconnect.dto.gp.trustee.TrusteeDetailsResponse;
import com.api.elifeconnect.service.gp.TrusteeService;
import com.api.elifeconnect.Utility.WebClientUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrusteeServiceImpl implements TrusteeService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

    @Value("${elife.api.gp.trustee.details.url}")
    private String eLifeApiTrusteeDetailsUrl;

    public TrusteeServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public TrusteeDetailsResponse getTrusteeDetails(TrusteeDetailsRequest request) {
        String url = eLifeApiBaseUrl + eLifeApiTrusteeDetailsUrl;
        log.info("URL::" + url);
        return client.post(url, request, Map.of(), TrusteeDetailsResponse.class).block();
    }

}

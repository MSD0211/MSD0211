package com.api.elifeconnect.service.gp.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.dto.gp.scheme.SchemeFundBalanceRequest;
import com.api.elifeconnect.service.gp.SchemeService;
import com.api.elifeconnect.Utility.WebClientUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SchemeServiceImpl implements SchemeService{

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

    @Value("${elife.api.gp.scheme.fund.balance.url}")
    private String eLifeApiSchemeFundBalanceUrl;

    public SchemeServiceImpl(WebClientUtil client) {
        this.client = client;
    }

 
    @Override
    public byte[] generateSchemeFundBalance(SchemeFundBalanceRequest request) {

    String url = eLifeApiBaseUrl + eLifeApiSchemeFundBalanceUrl;
    log.error("URL::"+url);

    return client.downloadPdf(url, request, Map.of()).block();

    }

}

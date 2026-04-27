package com.api.elifeconnect.service.gp.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.dto.gp.scheme.SchemeFundBalanceRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsResponse;
import com.api.elifeconnect.service.gp.SchemeService;
import com.api.elifeconnect.Utility.WebClientUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SchemeServiceImpl implements SchemeService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

    @Value("${elife.api.gp.scheme.fund.balance.url}")
    private String eLifeApiSchemeFundBalanceUrl;

    @Value("${elife.api.gp.scheme.profile.information.url}")
    private String eLifeApiSchemeProfileInformationUrl;

    @Value("${elife.api.gp.scheme.profile.url}")
    private String eLifeApiSchemeProfileUrl;

    @Value("${elife.api.gp.scheme.member.details.url}")
    private String eLifeApiSchemeMemberDetailsUrl;

    public SchemeServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public byte[] generateSchemeFundBalance(SchemeFundBalanceRequest request) {
        String url = eLifeApiBaseUrl + eLifeApiSchemeFundBalanceUrl;
        log.error("URL::" + url);
        return client.downloadPdf(url, request, Map.of()).block();
    }

    @Override
    public SchemeProfileInformationResponse getSchemeProfileInformation(SchemeProfileInformationRequest request) {
        String url = eLifeApiBaseUrl + eLifeApiSchemeProfileInformationUrl;
        log.error("URL::" + url);
        return client.post(url, request, Map.of(), SchemeProfileInformationResponse.class).block();
    }

    @Override
    public SchemeProfileResponse getSchemeProfile(SchemeProfileRequest request) {
        String url = eLifeApiBaseUrl + eLifeApiSchemeProfileUrl;
        log.info("URL::" + url);
        return client.post(url, request, Map.of(), SchemeProfileResponse.class).block();
    }

    @Override
    public SchemeMemberDetailsResponse getSchemeMemberDetails(SchemeMemberDetailsRequest request) {
        String url = eLifeApiBaseUrl + eLifeApiSchemeMemberDetailsUrl;
        log.info("URL::" + url);
        return client.post(url, request, Map.of(), SchemeMemberDetailsResponse.class).block();
    }

}

package com.api.elifeconnect.service.gp.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.gp.member.ContributionRecordRequestDTO;
import com.api.elifeconnect.service.gp.ContributionRecordService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContributionRecordServiceImpl implements ContributionRecordService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

    @Value("${elife.api.gp.contribution.record.url}")
    private String eLifeApiContributionRecordUrl;

    public ContributionRecordServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public byte[] generateContributionRecord(ContributionRecordRequestDTO request) {
        String url = eLifeApiBaseUrl + eLifeApiContributionRecordUrl;
        log.info("ContributionRecord URL::" + url);
        return client.downloadPdf(url, request, Map.of()).block();
    }
}
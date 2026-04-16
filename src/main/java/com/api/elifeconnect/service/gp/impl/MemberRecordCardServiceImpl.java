package com.api.elifeconnect.service.gp.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.gp.member.MemberRecordCardRequestDTO;
import com.api.elifeconnect.service.gp.MemberRecordCardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberRecordCardServiceImpl implements MemberRecordCardService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

    @Value("${elife.api.gp.member.record.card.url}")
    private String eLifeApiMemberRecordCardUrl;

    public MemberRecordCardServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public byte[] generateMemberRecordCard(MemberRecordCardRequestDTO request) {
        String url = eLifeApiBaseUrl + eLifeApiMemberRecordCardUrl;
        log.info("MemberRecordCard URL::" + url);
        return client.downloadPdf(url, request, Map.of()).block();
    }
}
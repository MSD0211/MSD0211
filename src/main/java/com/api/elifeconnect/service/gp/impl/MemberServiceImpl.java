package com.api.elifeconnect.service.gp.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.dto.gp.member.MemberStatementRequestDTO;
import com.api.elifeconnect.service.gp.MemberService;
import com.api.elifeconnect.Utility.WebClientUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

   
    @Value("${elife.api.gp.member.statement.url}")
    private String eLifeApiGpMemberStatementUrl;

    public MemberServiceImpl(WebClientUtil client) {
        this.client = client;
    }

 
    @Override
    public byte[] generateMemberStatement(MemberStatementRequestDTO request) {

    String url = eLifeApiBaseUrl + eLifeApiGpMemberStatementUrl;
    log.error("URL::"+url);

    return client.downloadPdf(url, request, Map.of()).block();

    }

}

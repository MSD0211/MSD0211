package com.api.elifeconnect.service.gp.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.dto.gp.member.MemberFundSummaryRequest;
import com.api.elifeconnect.dto.gp.member.MemberFundSummaryResponse;
import com.api.elifeconnect.dto.gp.member.MemberRecordCardRequest;
import com.api.elifeconnect.dto.gp.member.MemberStatementRequest;
import com.api.elifeconnect.service.gp.MemberService;
import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.gp.member.MemberDetailsRequest;
import com.api.elifeconnect.dto.gp.member.MemberDetailsResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

    @Value("${elife.api.gp.member.statement.url}")
    private String eLifeApiGpMemberStatementUrl;

    @Value("${elife.api.gp.member.record.card.url}")
    private String eLifeApiMemberRecordCardUrl;

    @Value("${elife.api.gp.member.fund.summary.url}")
    private String eLifeApiMemberFundSummaryUrl;

    public MemberServiceImpl(WebClientUtil client) {
        this.client = client;
    }

 
    @Override
    public byte[] generateMemberStatement(MemberStatementRequest request) {

    String url = eLifeApiBaseUrl + eLifeApiGpMemberStatementUrl;
    log.error("URL::"+url);

    return client.downloadPdf(url, request, Map.of()).block();

    }

    @Override
    public byte[] generateMemberRecordCard(MemberRecordCardRequest request){

         String url = eLifeApiBaseUrl + eLifeApiMemberRecordCardUrl;
        log.info("MemberRecordCard URL::" + url);
        return client.downloadPdf(url, request, Map.of()).block();
    }

    @Override
    public MemberFundSummaryResponse generateMemberFundSummary(MemberFundSummaryRequest request) {
                String url = eLifeApiBaseUrl + eLifeApiMemberFundSummaryUrl;
        log.info("REQUEST REFERENCE ID::"+request.referenceId());
        log.info("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, request, Map.of(), MemberFundSummaryResponse.class)
                     .block();   
    }
     @Override
    public MemberDetailsResponse getMemberDetails(MemberDetailsRequest request) {
        String url = elifeBaseUrl + "/api/v1/gp/member/details";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = new HashMap<>();
            body.put("schemeNumber", request.schemeNumber());
            body.put("memberId", request.memberId());

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<MemberDetailsResponse> response = restTemplate.exchange(
                    url, HttpMethod.POST, entity, MemberDetailsResponse.class);

            return response.getBody();

        } catch (ApiException ex) {
            throw ex;
        } catch (Exception e) {
            log.error("Error retrieving Member Details for schemeNumber: {}, memberId: {}",
                    request.schemeNumber(), request.memberId(), e);
            throw new ApiException("Member Details could not be retrieved for Scheme Number: "
                    + request.schemeNumber() + " / Member ID: " + request.memberId());
        }
    }


}

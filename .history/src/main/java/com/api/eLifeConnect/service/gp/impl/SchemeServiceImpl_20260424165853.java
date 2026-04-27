package com.api.elifeconnect.service.gp.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.dto.gp.scheme.SchemeFundBalanceRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationResponse;
import com.api.elifeconnect.service.gp.SchemeService;
import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SchemeServiceImpl implements SchemeService{

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

    @Value("${elife.api.gp.scheme.fund.balance.url}")
    private String eLifeApiSchemeFundBalanceUrl;

    @Value("${elife.api.gp.scheme.profile.information.url}")
    private String eLifeApiSchemeProfileInformationUrl;
    

    public SchemeServiceImpl(WebClientUtil client) {
        this.client = client;
    }

 
    @Override
    public byte[] generateSchemeFundBalance(SchemeFundBalanceRequest request) {

    String url = eLifeApiBaseUrl + eLifeApiSchemeFundBalanceUrl;
    log.error("URL::"+url);

    return client.downloadPdf(url, request, Map.of()).block();

    }

    @Override
    public SchemeProfileInformationResponse getSchemeProfileInformation(SchemeProfileInformationRequest request) {
        String url = eLifeApiBaseUrl + eLifeApiSchemeProfileInformationUrl;
        log.error("URL::"+url);
        return client.post(url, request, Map.of(), SchemeProfileInformationResponse.class).block();
    }
    @Override
    public SchemeProfileResponse getSchemeProfile(SchemeProfileRequest request) {
        String url = elifeBaseUrl + "/api/v1/gp/scheme/profile";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("schemeNumber", request.schemeNumber());

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<SchemeProfileResponse> response = restTemplate.exchange(
                    url, HttpMethod.POST, entity, SchemeProfileResponse.class);

            return response.getBody();

        } catch (ApiException ex) {
            throw ex;
        } catch (Exception e) {
            log.error("Error retrieving Scheme Profile for schemeNumber: {}", request.schemeNumber(), e);
            throw new ApiException("Scheme Profile could not be retrieved for Scheme Number: "
                    + request.schemeNumber());
        }
    }

    @Override
    public SchemeMemberDetailsResponse getSchemeMemberDetails(SchemeMemberDetailsRequest request) {
        String url = elifeBaseUrl + "/api/v1/gp/scheme/member/details";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("schemeNumber", request.schemeNumber());

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<SchemeMemberDetailsResponse> response = restTemplate.exchange(
                    url, HttpMethod.POST, entity, SchemeMemberDetailsResponse.class);

            return response.getBody();

        } catch (ApiException ex) {
            throw ex;
        } catch (Exception e) {
            log.error("Error retrieving Scheme Member Details for schemeNumber: {}", request.schemeNumber(), e);
            throw new ApiException("Scheme Member Details could not be retrieved for Scheme Number: "
                    + request.schemeNumber());
        }
    }

}

package com.api.elifeconnect.service.proposal.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.proposal.ProposalPremiumEnquiryRequest;
import com.api.elifeconnect.dto.proposal.ProposalPremiumEnquiryResponse;
import com.api.elifeconnect.dto.proposal.ProposalSubmissionEnquiryRequest;
import com.api.elifeconnect.dto.proposal.ProposalSubmissionEnquiryResponse;
import com.api.elifeconnect.dto.proposal.submit.ProposalSubmitRequest;
import com.api.elifeconnect.dto.proposal.submit.ProposalSubmitResponse;
import com.api.elifeconnect.service.proposal.ProposalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProposalServiceImpl implements ProposalService {

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;
    
    @Value("${elife.api.proposal.premium.enquiry.url}")
    private String eLifeApiProposalPremiumEnquiryUrl;

    @Value("${elife.api.proposal.submission.enquiry.url}")
    private String eLifeApiProposalSubmissionEnquiryUrl;

      @Value("${elife.api.proposal.submit.url}")
    private String eLifeApiProposalSubmitUrl;


    public ProposalServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public ProposalPremiumEnquiryResponse proposalPremiumEnquiry(ProposalPremiumEnquiryRequest req){
        String url = eLifeApiBaseUrl + eLifeApiProposalPremiumEnquiryUrl;
        log.error("REQUEST REFERENCE ID::"+req.referenceNumber());
        log.error("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), ProposalPremiumEnquiryResponse.class)
                     .block();   // convert Mono → object
    }

    @Override
    public ProposalSubmissionEnquiryResponse proposalSubmissionEnquiry(ProposalSubmissionEnquiryRequest req){
        String url = eLifeApiBaseUrl + eLifeApiProposalSubmissionEnquiryUrl;
        log.error("REQUEST REFERENCE ID::"+req.referenceNumber());
        log.error("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), ProposalSubmissionEnquiryResponse.class)
                     .block();   // convert Mono → object
    }
    
    @Override
    public ProposalSubmitResponse proposalSubmit(ProposalSubmitRequest req){
        String url = eLifeApiBaseUrl + eLifeApiProposalSubmitUrl;
        log.error("URL::"+url);

        // Call WebClientUtil (reactive) and block for MVC
        return client.post(url, req, Map.of(), ProposalSubmitResponse.class)
                     .block();   // convert Mono → object
    }

    
}


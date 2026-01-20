package com.api.elifeconnect.service.loan.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.dto.loan.LoanQuotationRequest;
import com.api.elifeconnect.service.LoanService;
import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.loan.LoanEnquiryRequest;
import com.api.elifeconnect.dto.loan.LoanEnquiryResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoanServiceImpl implements LoanService{

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

    @Value("${elife.api.loan.quotation.url}")
    private String eLifeApiLoanQuotationUrl;

    public LoanServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public byte[] generateLoanQuotation(LoanQuotationRequest loanQuotationRequest){

    String url = eLifeApiBaseUrl + eLifeApiLoanQuotationUrl;
    log.error("URL::"+url);

    return client.downloadPdf(url, loanQuotationRequest, Map.of()).block();
    
    
    }

}

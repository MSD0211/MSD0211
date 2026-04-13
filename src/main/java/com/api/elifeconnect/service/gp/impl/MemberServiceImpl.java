package com.api.elifeconnect.service.gp.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.elifeconnect.dto.loan.LoanQuotationRequest;
import com.api.elifeconnect.service.gp.MemberService;
import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.dto.loan.LoanRepaymentLetterRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

    private final WebClientUtil client;

    @Value("${elife.api.baseurl}")
    private String eLifeApiBaseUrl;

    @Value("${elife.api.loan.quotation.url}")
    private String eLifeApiLoanQuotationUrl;

    @Value("${elife.api.loan.repayment.letter.url}")
    private String eLifeApiLoanRepaymentLetterUrl;

    public MemberServiceImpl(WebClientUtil client) {
        this.client = client;
    }

    @Override
    public byte[] generateLoanQuotation(LoanQuotationRequest loanQuotationRequest){

    String url = eLifeApiBaseUrl + eLifeApiLoanQuotationUrl;
    log.error("URL::"+url);

    return client.downloadPdf(url, loanQuotationRequest, Map.of()).block();
    
    
    }

    @Override
    public byte[] generateLoanRepaymentLetter(LoanRepaymentLetterRequest loanRepaymentLetterRequest){

    String url = eLifeApiBaseUrl + eLifeApiLoanRepaymentLetterUrl;
    log.error("URL::"+url);

    return client.downloadPdf(url, loanRepaymentLetterRequest, Map.of()).block();
    
    
    }

}

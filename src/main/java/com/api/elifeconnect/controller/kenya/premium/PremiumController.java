package com.api.elifeconnect.controller.kenya.premium;


import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.premium.statement.PremiumStatementFullRequest;
import com.api.elifeconnect.dto.premium.statement.PremiumStatementFullResponse;
import com.api.elifeconnect.dto.premium.summary.PremiumSummaryRequest;
import com.api.elifeconnect.dto.premium.summary.PremiumSummaryResponse;
import com.api.elifeconnect.dto.proposal.ProposalPremiumEnquiryRequest;
import com.api.elifeconnect.dto.proposal.ProposalPremiumEnquiryResponse;
import com.api.elifeconnect.dto.proposal.ProposalSubmissionEnquiryRequest;
import com.api.elifeconnect.dto.proposal.ProposalSubmissionEnquiryResponse;
import com.api.elifeconnect.dto.proposal.submit.ProposalSubmitRequest;
import com.api.elifeconnect.dto.proposal.submit.ProposalSubmitResponse;
import com.api.elifeconnect.service.premium.PremiumService;
import com.api.elifeconnect.service.proposal.ProposalService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("kenyaPremiumController")
@RequestMapping("/api/v1/kenya/premium")
public class PremiumController {

    private final PremiumService premiumService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public PremiumController(PremiumService premiumService) {
        this.premiumService = premiumService;
        this.responseBuilder = new ApiResponseBuilder();
    }


    @PostMapping("/statement")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("Premium Statement API")
    public ResponseEntity<ApiResponse<PremiumStatementFullResponse>> getPremiumStatementFull(
            @RequestBody PremiumStatementFullRequest premiumStatementFullRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "Premium Statement API");
        PremiumStatementFullResponse response = premiumService.premiumStatementFull(premiumStatementFullRequest);
        System.out.println("RESPONSE ::"+response.policyNumber());
        System.out.println("RESPONSE::"+response.firstPremium());
        ApiResponse<PremiumStatementFullResponse> body =
                responseBuilder.success(request, "Proposal Premium Enquiry done", response);

        return ResponseEntity.ok(body);
    }

    @PostMapping("/summary")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("Premium Summary API")
    public ResponseEntity<ApiResponse<PremiumSummaryResponse>> getPremiumSummary(
            @RequestBody PremiumSummaryRequest premiumSummaryRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "Premium Summary API");
        PremiumSummaryResponse response = premiumService.premiumStatementFull(premiumSummaryRequest);
        System.out.println("RESPONSE ::"+response.policyNumber());
        System.out.println("RESPONSE::"+response.sumAssured());
        ApiResponse<PremiumSummaryResponse> body =
                responseBuilder.success(request, "Proposal Premium Enquiry done", response);

        return ResponseEntity.ok(body);
    }
}

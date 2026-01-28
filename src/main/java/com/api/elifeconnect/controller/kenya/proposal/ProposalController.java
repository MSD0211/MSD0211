package com.api.elifeconnect.controller.kenya.proposal;


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
import com.api.elifeconnect.dto.proposal.ProposalPremiumEnquiryRequest;
import com.api.elifeconnect.dto.proposal.ProposalPremiumEnquiryResponse;
import com.api.elifeconnect.dto.proposal.ProposalSubmissionEnquiryRequest;
import com.api.elifeconnect.dto.proposal.ProposalSubmissionEnquiryResponse;
import com.api.elifeconnect.dto.proposal.submit.ProposalSubmitRequest;
import com.api.elifeconnect.dto.proposal.submit.ProposalSubmitResponse;
import com.api.elifeconnect.service.proposal.ProposalService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("kenyaPremiumController")
@RequestMapping("/api/v1/kenya/proposal")
public class ProposalController {

    private final ProposalService proposalService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
        this.responseBuilder = new ApiResponseBuilder();
    }


    @PostMapping("/premium/enquiry")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("Proposal Premium Enquiry API")
    public ResponseEntity<ApiResponse<ProposalPremiumEnquiryResponse>> proposalPremiumEnquiry(
            @RequestBody ProposalPremiumEnquiryRequest proposalPremiumEnquiryRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "Proposal Premium Enquiry API");
        ProposalPremiumEnquiryResponse response = proposalService.proposalPremiumEnquiry(proposalPremiumEnquiryRequest);
        System.out.println("RESPONSE ::"+response.proposalNumber());
        System.out.println("RESPONSE::"+response.installmentPremium());
        ApiResponse<ProposalPremiumEnquiryResponse> body =
                responseBuilder.success(request, "Proposal Premium Enquiry done", response);

        return ResponseEntity.ok(body);
    }

    @PostMapping("/submission/enquiry")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("Proposal Submission Enquiry API")
    public ResponseEntity<ApiResponse<ProposalSubmissionEnquiryResponse>> proposalPremiumEnquiry(
            @RequestBody ProposalSubmissionEnquiryRequest proposalSubmissionEnquiryRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "Proposal Submission Enquiry API");
        ProposalSubmissionEnquiryResponse response;
        log.error("PROPOSAL SUBMISSION!!");
        response = proposalService.proposalSubmissionEnquiry(proposalSubmissionEnquiryRequest);
        log.error("RESPONSE ::"+response.proposalNumber());
        log.error("RESPONSE::"+response.installmentPremium());
        ApiResponse<ProposalSubmissionEnquiryResponse> body =
                responseBuilder.success(request, "Proposal Submission Enquiry done", response);

        return ResponseEntity.ok(body);
    }

    @PostMapping("/submit")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("Proposal Submission API")
    public ResponseEntity<ApiResponse<ProposalSubmitResponse>> proposalSubmit(
            @RequestBody ProposalSubmitRequest proposalSubmitRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "Proposal Submission API");
        log.error("REFERENCE ID::"+proposalSubmitRequest.id());
        ProposalSubmitResponse response;
        log.error("PROPOSAL SUBMISSION!!");
        response = proposalService.proposalSubmit(proposalSubmitRequest);
        log.error("RESPONSE ::"+response.message());
        ApiResponse<ProposalSubmitResponse  > body =
                responseBuilder.success(request, "Proposal Submission done", response);

        return ResponseEntity.ok(body);
    }


}

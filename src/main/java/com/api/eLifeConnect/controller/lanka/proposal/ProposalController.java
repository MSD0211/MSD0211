package com.api.elifeconnect.controller.lanka.proposal;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.aop.ApiShortNames;
import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.proposal.ProposalPremiumEnquiryRequest;
import com.api.elifeconnect.dto.proposal.ProposalPremiumEnquiryResponse;
import com.api.elifeconnect.dto.proposal.ProposalSubmissionEnquiryRequest;
import com.api.elifeconnect.dto.proposal.ProposalSubmissionEnquiryResponse;
import com.api.elifeconnect.dto.proposal.deposit.ProposalDepositCreateRequest;
import com.api.elifeconnect.dto.proposal.deposit.ProposalDepositCreateResponse;
import com.api.elifeconnect.dto.proposal.submit.ProposalSubmitRequest;
import com.api.elifeconnect.dto.proposal.submit.ProposalSubmitResponse;
import com.api.elifeconnect.service.proposal.ProposalService;
import com.api.elifeconnect.validation.groups.LankaGroup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController("lankaProposalController")
@RequestMapping("/api/v1/lanka/proposal")
@Tag(name = "Proposal Management", description = "Endpoints for Lanka proposal enquiry, submission, and deposit operations")
public class ProposalController {

        private final ProposalService proposalService;
        private final ApiResponseBuilder responseBuilder;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        public ProposalController(ProposalService proposalService) {
                this.proposalService = proposalService;
                this.responseBuilder = new ApiResponseBuilder();
        }

        @PostMapping("/premium/enquiry")
        @PreAuthorize("hasAuthority('lanka.api.read')")
        @LogApiCall(value = "Proposal Premium Enquiry API", shortName = ApiShortNames.PROPOSAL_PREMIUM_ENQ)
        @Operation(summary = "Proposal Premium Enquiry", description = "Calculates installment premium and details for a proposed policy.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Proposal premium enquiry successful"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<ProposalPremiumEnquiryResponse>> proposalPremiumEnquiry(
                        @Valid @RequestBody ProposalPremiumEnquiryRequest proposalPremiumEnquiryRequest,
                        HttpServletRequest request) {

                MDC.put("apiName", "Proposal Premium Enquiry API");
                ProposalPremiumEnquiryResponse response = proposalService
                                .proposalPremiumEnquiry(proposalPremiumEnquiryRequest);
                System.out.println("RESPONSE ::" + response.proposalNumber());
                System.out.println("RESPONSE::" + response.installmentPremium());
                ApiResponse<ProposalPremiumEnquiryResponse> body = responseBuilder.success(request,
                                "Proposal Premium Enquiry done", response);

                return ResponseEntity.ok(body);
        }

        @PostMapping("/submission/enquiry")
        @PreAuthorize("hasAuthority('lanka.api.read')")
        @LogApiCall(value = "Proposal Submission Enquiry API", shortName = ApiShortNames.PROPOSAL_SUBMISSION_ENQ)
        @Operation(summary = "Proposal Submission Enquiry", description = "Checks proposal submission status and retrieves installment premium information.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Proposal submission enquiry successful"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<ProposalSubmissionEnquiryResponse>> proposalPremiumEnquiry(
                        @Valid @RequestBody ProposalSubmissionEnquiryRequest proposalSubmissionEnquiryRequest,
                        HttpServletRequest request) {

                MDC.put("apiName", "Proposal Submission Enquiry API");
                ProposalSubmissionEnquiryResponse response;
                log.error("PROPOSAL SUBMISSION!!");
                response = proposalService.proposalSubmissionEnquiry(proposalSubmissionEnquiryRequest);
                log.error("RESPONSE ::" + response.proposalNumber());
                log.error("RESPONSE::" + response.installmentPremium());
                ApiResponse<ProposalSubmissionEnquiryResponse> body = responseBuilder.success(request,
                                "Proposal Submission Enquiry done", response);

                return ResponseEntity.ok(body);
        }

        @PostMapping("/submit")
        @PreAuthorize("hasAuthority('lanka.api.read')")
        @LogApiCall(value = "Proposal Submission API", shortName = ApiShortNames.PROPOSAL_SUBMISSION)
        @Operation(summary = "Submit New Proposal", description = "Submits a new policy proposal with required applicant and payment details.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Proposal successfully submitted"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<ProposalSubmitResponse>> proposalSubmit(
                        @Validated({ Default.class,
                                        LankaGroup.class }) @RequestBody ProposalSubmitRequest proposalSubmitRequest,
                        HttpServletRequest request) {

                MDC.put("apiName", "Proposal Submission API");
                log.error("REFERENCE ID::" + proposalSubmitRequest.id());
                ProposalSubmitResponse response;
                log.error("PROPOSAL SUBMISSION!!");
                response = proposalService.proposalSubmit(proposalSubmitRequest);
                log.error("RESPONSE ::" + response.message());
                ApiResponse<ProposalSubmitResponse> body = responseBuilder.success(request, "Proposal Submission done",
                                response);

                return ResponseEntity.ok(body);
        }

        @PostMapping("/deposit/create")
        @PreAuthorize("hasAuthority('lanka.api.read')")
        @LogApiCall(value = "Proposal Deposit Create API", shortName = ApiShortNames.PROPOSAL_DEPOSIT_CREATE)
        @Operation(summary = "Create Proposal Deposit", description = "Creates a deposit entry for an existing or pending proposal.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Proposal deposit successfully created"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<ProposalDepositCreateResponse>> proposalSubmit(
                        @Valid @RequestBody ProposalDepositCreateRequest proposalDepositCreateRequest,
                        HttpServletRequest request) throws JsonProcessingException {

                log.info(
                                "REQUEST BODY JSON: {}",
                                objectMapper.writeValueAsString(proposalDepositCreateRequest));
                MDC.put("apiName", "Proposal Submission API");
                log.error("REFERENCE ID::" + proposalDepositCreateRequest.id());
                ProposalDepositCreateResponse response;
                log.error("PROPOSAL SUBMISSION!!");
                response = proposalService.createProposalDeposit(proposalDepositCreateRequest);
                log.error("RESPONSE ::" + response.message());
                ApiResponse<ProposalDepositCreateResponse> body = responseBuilder.success(request,
                                "Proposal Deposit Creation done", response);

                return ResponseEntity.ok(body);
        }

}

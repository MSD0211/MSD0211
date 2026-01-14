package com.api.elifeconnect.controller.lanka.policy;

import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.service.policy.PolicyService;


import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.dto.policy.AgentPolicyEnquiryRequest;
import com.api.elifeconnect.dto.policy.AgentPolicyEnquiryResponse;
import com.api.elifeconnect.dto.policy.CustomerPolicyEnquiryRequest;
import com.api.elifeconnect.dto.policy.CustomerPolicyEnquiryResponse;
import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentResponse;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryResponse;
import com.api.elifeconnect.service.premium.PremiumService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/lanka/policy/enquiry")
public class PolicyController {

    private final PolicyService policyService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/customer")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("CustomerPolicyEnquiryAPI")
    public ResponseEntity<ApiResponse<CustomerPolicyEnquiryResponse>> customerPolicyEnquiry(
            @RequestBody CustomerPolicyEnquiryRequest customerPolicyEnqRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "CustomerPolicyEnquiryAPI");
        CustomerPolicyEnquiryResponse response = policyService.customerPolicyEnquiry(customerPolicyEnqRequest);
        System.out.println("RESPONSE ::"+response.message());
        System.out.println("RESPONSE::"+response.policyDetails().stream().toString());
        ApiResponse<CustomerPolicyEnquiryResponse> body =
                responseBuilder.success(request, "Customer policy enquiry success", response);

        return ResponseEntity.ok(body);
    }

    @PostMapping("/agent")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("CustomerPolicyEnquiryAPI")
    public ResponseEntity<ApiResponse<AgentPolicyEnquiryResponse>> agentPolicyEnquiry(
            @RequestBody AgentPolicyEnquiryRequest agentPolicyEnqRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "AgentPolicyEnquiryAPI");
        AgentPolicyEnquiryResponse response = policyService.agentPolicyEnquiry(agentPolicyEnqRequest);
        System.out.println("RESPONSE ::"+response.message());
        System.out.println("RESPONSE::"+response.policyDetails().stream().toString());
        ApiResponse<AgentPolicyEnquiryResponse> body =
                responseBuilder.success(request, "Agent Policy enquiry success", response);

        return ResponseEntity.ok(body);
    }
}

package com.api.elifeconnect.controller.lanka.policy;

import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.service.policy.PolicyService;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.aop.ApiShortNames;
import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.dto.policy.AgentPolicyEnquiryRequest;
import com.api.elifeconnect.dto.policy.AgentPolicyEnquiryResponse;
import com.api.elifeconnect.dto.policy.CustomerPolicyEnquiryRequest;
import com.api.elifeconnect.dto.policy.CustomerPolicyEnquiryResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestController("lankaPolicyController")
@RequestMapping("/api/v1/lanka/policy")
public class PolicyController {

    private final PolicyService policyService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/enquiry/customer")
    @PreAuthorize("hasAuthority('lanka.api.read')")
    @LogApiCall(value = "CustomerPolicyEnquiryAPI", shortName = "PLCENQ")
    public ResponseEntity<ApiResponse<CustomerPolicyEnquiryResponse>> customerPolicyEnquiry(
            @Valid @RequestBody CustomerPolicyEnquiryRequest customerPolicyEnqRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "CustomerPolicyEnquiryAPI");
        CustomerPolicyEnquiryResponse response = policyService.customerPolicyEnquiry(customerPolicyEnqRequest);
        System.out.println("RESPONSE ::" + response.message());
        System.out.println("RESPONSE::" + response.policyDetails().stream().toString());
        ApiResponse<CustomerPolicyEnquiryResponse> body = responseBuilder.success(request,
                "Customer policy enquiry success", response);

        return ResponseEntity.ok(body);
    }

    @PostMapping("/enquiry/agent")
    @PreAuthorize("hasAuthority('lanka.api.read')")
    @LogApiCall(value = "CustomerPolicyEnquiryAPI", shortName = ApiShortNames.POLICY_ENQUIRY)
    public ResponseEntity<ApiResponse<AgentPolicyEnquiryResponse>> agentPolicyEnquiry(
            @Valid @RequestBody AgentPolicyEnquiryRequest agentPolicyEnqRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "AgentPolicyEnquiryAPI");
        AgentPolicyEnquiryResponse response = policyService.agentPolicyEnquiry(agentPolicyEnqRequest);
        System.out.println("RESPONSE ::" + response.message());
        System.out.println("RESPONSE::" + response.policyDetails().stream().toString());
        ApiResponse<AgentPolicyEnquiryResponse> body = responseBuilder.success(request, "Agent Policy enquiry success",
                response);

        return ResponseEntity.ok(body);
    }
}

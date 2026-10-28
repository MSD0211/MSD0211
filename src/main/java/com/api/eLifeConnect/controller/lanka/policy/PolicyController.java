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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController("lankaPolicyController")
@RequestMapping("/api/v1/lanka/policy")
@Tag(name = "Policy Management", description = "Endpoints for Lanka policy enquiry and policy details")
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
    @Operation(summary = "Customer Policy Enquiry", description = "Retrieves policy details for a customer based on policy number and verification criteria.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Customer policy enquiry successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
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
    @Operation(summary = "Agent Policy Enquiry", description = "Retrieves policy details on behalf of an agent.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Agent policy enquiry successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
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

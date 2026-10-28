package com.api.elifeconnect.controller.nepal.policy;

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
import com.api.elifeconnect.dto.policy.PolicyDetailsRequest;
import com.api.elifeconnect.dto.policy.PolicyDetailsResponse;
import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController("nepalPolicyController")
@RequestMapping("/api/v1/nepal/policy")
@Tag(name = "Policy Management", description = "Endpoints for Nepal policy enquiry and details")
public class PolicyController {

    private final PolicyService policyService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/details")
    @PreAuthorize("hasAuthority('nepal.api.read')")
    @LogApiCall(value = "PolicyDetailsAPI", shortName = ApiShortNames.POLICY_DETAILS)
    @Operation(summary = "Fetch Policy Details", description = "Fetches comprehensive policy details and mode information for Nepal policy.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Policy details retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<PolicyDetailsResponse>> fetchPolicyDetails(
            @Valid @RequestBody PolicyDetailsRequest policyDetailsRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "PolicyDetailsAPI");
        PolicyDetailsResponse response = policyService.fetchPolicyDetails(policyDetailsRequest);
        System.out.println("RESPONSE ::" + response.polMode());
        System.out.println("RESPONSE::" + response.planName());
        ApiResponse<PolicyDetailsResponse> body = responseBuilder.success(request,
                "Policy details fetching success", response);

        return ResponseEntity.ok(body);
    }
}

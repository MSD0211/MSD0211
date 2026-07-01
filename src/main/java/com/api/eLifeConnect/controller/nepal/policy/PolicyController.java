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
import com.api.elifeconnect.dto.policy.PolicyDetailsRequest;
import com.api.elifeconnect.dto.policy.PolicyDetailsResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestController("nepalPolicyController")
@RequestMapping("/api/v1/nepal/policy")
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

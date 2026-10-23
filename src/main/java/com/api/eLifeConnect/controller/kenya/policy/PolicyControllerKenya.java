package com.api.elifeconnect.controller.kenya.policy;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.aop.ApiShortNames;
import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.policy.PolicyRevivalQuotationRequest;
import com.api.elifeconnect.dto.policy.PolicyRevivalQuotationResponse;
import com.api.elifeconnect.service.policy.PolicyService;

import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/kenya/policy")
@Tag(name = "Policy Management", description = "Endpoints for Kenya policy revival quotation and policy details")
public class PolicyControllerKenya {

    private final PolicyService policyService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public PolicyControllerKenya(PolicyService policyService) {
        this.policyService = policyService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/revival/quotation")
    // @PreAuthorize("hasAnyRole('USER')")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "Revival Quotation API", shortName = ApiShortNames.POLICY_REVIVAL_QUOT)
    @Operation(summary = "Policy Revival Quotation", description = "Calculates revival quotation and details for a lapsed Kenya policy.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Policy revival details retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<PolicyRevivalQuotationResponse>> fetchPolicyRevivalDetails(
            @RequestBody PolicyRevivalQuotationRequest policyRevivalQuotationRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "Revival Quotation API");
        PolicyRevivalQuotationResponse response = policyService
                .fetchPolicyRevivalDetails(policyRevivalQuotationRequest);
        System.out.println("RESPONSE ::" + response.policyNumber());
        System.out.println("RESPONSE::" + response.durationOfPremiumPaid());
        ApiResponse<PolicyRevivalQuotationResponse> body = responseBuilder.success(request,
                "Policy Revival Quotation done", response);

        return ResponseEntity.ok(body);
    }

}

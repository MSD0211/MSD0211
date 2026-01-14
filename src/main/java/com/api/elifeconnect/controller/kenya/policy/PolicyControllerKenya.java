package com.api.elifeconnect.controller.kenya.policy;


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
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.policy.PolicyRevivalQuotationRequest;
import com.api.elifeconnect.dto.policy.PolicyRevivalQuotationResponse;
import com.api.elifeconnect.service.policy.PolicyService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/kenya/policy")
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
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("Revival Quotation API")
    public ResponseEntity<ApiResponse<PolicyRevivalQuotationResponse>> fetchPolicyRevivalDetails(
            @RequestBody PolicyRevivalQuotationRequest policyRevivalQuotationRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "Revival Quotation API");
        PolicyRevivalQuotationResponse response = policyService.fetchPolicyRevivalDetails(policyRevivalQuotationRequest);
        System.out.println("RESPONSE ::"+response.policyNumber());
        System.out.println("RESPONSE::"+response.durationOfPremiumPaid());
        ApiResponse<PolicyRevivalQuotationResponse> body =
                responseBuilder.success(request, "Policy Revival Quotation done", response);

        return ResponseEntity.ok(body);
    }

}

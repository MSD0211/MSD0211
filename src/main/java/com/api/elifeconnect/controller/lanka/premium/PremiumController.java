package com.api.elifeconnect.controller.lanka.premium;

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
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentResponse;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryResponse;
import com.api.elifeconnect.service.premium.PremiumService;

import jakarta.servlet.http.HttpServletRequest;

@RestController("lankaPremiumController")
@RequestMapping("/api/v1/lanka/premium")
public class PremiumController {

    private final PremiumService premiumService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public PremiumController(PremiumService premiumService) {
        this.premiumService = premiumService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/renewal/enquiry")
    @PreAuthorize("hasAuthority('lanka.api.read')")
    @LogApiCall(value = "RenewalPremiumEnquiryAPI", shortName = ApiShortNames.RENEWAL_PREMIUM_ENQ)
    public ResponseEntity<ApiResponse<RenewalPremiumEnquiryResponse>> loanEnquiry(
            @Valid @RequestBody RenewalPremiumEnquiryRequest renewalPremiumEnqRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "PremiumEnquiryAPI");
        RenewalPremiumEnquiryResponse response = premiumService.renewalPremiumEnquiry(renewalPremiumEnqRequest);
        System.out.println("RESPONSE ::" + response.httpStatus());
        System.out.println("RESPONSE::" + response.policy_details().amount());
        ApiResponse<RenewalPremiumEnquiryResponse> body = responseBuilder.success(request,
                "Renewal Premium enquiry success", response);

        return ResponseEntity.ok(body);
    }

    @PostMapping("/renewal/adjustment")
    @PreAuthorize("hasAuthority('lanka.api.read')")
    @LogApiCall(value = "RenewalPremiumAdjustmentEnquiryAPI", shortName = ApiShortNames.RENEWAL_PREMIUM_ADJ)
    public ResponseEntity<ApiResponse<RenewalPremiumAdjustmentResponse>> loanEnquiry(
            @Valid @RequestBody RenewalPremiumAdjustmentRequest renewalPremiumAdjRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "PremiumEnquiryAPI");
        RenewalPremiumAdjustmentResponse response = premiumService.renewalPremiumAdjustment(renewalPremiumAdjRequest);
        System.out.println("RESPONSE ::" + response.httpStatus());
        System.out.println("RESPONSE::" + response.fup());
        ApiResponse<RenewalPremiumAdjustmentResponse> body = responseBuilder.success(request,
                "Renewal Premium Adjustment success", response);

        return ResponseEntity.ok(body);
    }
}

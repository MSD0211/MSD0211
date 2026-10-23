package com.api.elifeconnect.controller.mauritius.premium;

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
import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentResponse;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryResponse;
import com.api.elifeconnect.service.premium.PremiumService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController("mauritiusPremiumController")
@RequestMapping("/api/v1/mauritius/premium")
@Tag(name = "Premium Services", description = "Endpoints for Mauritius renewal premium enquiry and adjustment")
public class PremiumController {

    private final PremiumService premiumService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public PremiumController(PremiumService premiumService) {
        this.premiumService = premiumService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/renewal/enquiry")
    @PreAuthorize("hasAuthority('mauritius.api.read')")
    @LogApiCall(value = "RenewalPremiumEnquiryAPI", shortName = ApiShortNames.RENEWAL_PREMIUM_ENQ)
    @Operation(summary = "Renewal Premium Enquiry", description = "Calculates renewal premium amount and details for Mauritius policy.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Renewal premium enquiry successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<RenewalPremiumEnquiryResponse>> PremiumEnquiry(
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
    @PreAuthorize("hasAuthority('mauritius.api.read')")
    @LogApiCall(value = "RenewalPremiumAdjustmentEnquiryAPI", shortName = ApiShortNames.RENEWAL_PREMIUM_ADJ)
    @Operation(summary = "Renewal Premium Adjustment", description = "Performs renewal premium adjustment for Mauritius policy.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Renewal premium adjustment successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<RenewalPremiumAdjustmentResponse>> RenewalPremiumAdjustment(
            @Valid @RequestBody RenewalPremiumAdjustmentRequest renewalPremiumAdjRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "PremiumAdjustmentAPI");
        RenewalPremiumAdjustmentResponse response = premiumService.renewalPremiumAdjustment(renewalPremiumAdjRequest);
        System.out.println("RESPONSE ::" + response.httpStatus());
        System.out.println("RESPONSE::" + response.fup());
        ApiResponse<RenewalPremiumAdjustmentResponse> body = responseBuilder.success(request,
                "Renewal Premium Adjustment success", response);

        return ResponseEntity.ok(body);
    }
}

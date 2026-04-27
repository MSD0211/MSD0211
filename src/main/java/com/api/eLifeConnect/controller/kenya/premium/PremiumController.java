package com.api.elifeconnect.controller.kenya.premium;

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
import com.api.elifeconnect.dto.premium.statement.PremiumStatementFullRequest;
import com.api.elifeconnect.dto.premium.statement.PremiumStatementFullResponse;
import com.api.elifeconnect.dto.premium.summary.PremiumSummaryRequest;
import com.api.elifeconnect.dto.premium.summary.PremiumSummaryResponse;
import com.api.elifeconnect.service.premium.PremiumService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("kenyaPremiumController")
@RequestMapping("/api/v1/kenya/premium")
public class PremiumController {

    private final PremiumService premiumService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public PremiumController(PremiumService premiumService) {
        this.premiumService = premiumService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/statement")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "Premium Statement API", shortName = ApiShortNames.PREMIUM_STATEMENT)
    public ResponseEntity<ApiResponse<PremiumStatementFullResponse>> getPremiumStatementFull(
            @Valid @RequestBody PremiumStatementFullRequest premiumStatementFullRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "Premium Statement API");
        PremiumStatementFullResponse response = premiumService.premiumStatementFull(premiumStatementFullRequest);
        System.out.println("RESPONSE ::" + response.policyNumber());
        System.out.println("RESPONSE::" + response.firstPremium());
        ApiResponse<PremiumStatementFullResponse> body = responseBuilder.success(request, "Premium Statement generated",
                response);

        return ResponseEntity.ok(body);
    }

    @PostMapping("/summary")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "Premium Summary API", shortName = ApiShortNames.PREMIUM_SUMMARY)
    public ResponseEntity<ApiResponse<PremiumSummaryResponse>> getPremiumSummary(
            @Valid @RequestBody PremiumSummaryRequest premiumSummaryRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "Premium Summary API");
        PremiumSummaryResponse response = premiumService.premiumStatementFull(premiumSummaryRequest);
        System.out.println("RESPONSE ::" + response.policyNumber());
        System.out.println("RESPONSE::" + response.sumAssured());
        ApiResponse<PremiumSummaryResponse> body = responseBuilder.success(request, "Premium Summary generated",
                response);

        return ResponseEntity.ok(body);
    }
}

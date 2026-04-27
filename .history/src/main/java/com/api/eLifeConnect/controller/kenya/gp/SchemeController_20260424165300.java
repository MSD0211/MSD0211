package com.api.elifeconnect.controller.kenya.gp;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import com.api.elifeconnect.dto.gp.scheme.SchemeFundBalanceRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationResponse;
import com.api.elifeconnect.service.gp.SchemeService;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/kenya/gp/scheme")
public class SchemeController {

    private final SchemeService schemeService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public SchemeController(SchemeService schemeService) {
        this.schemeService = schemeService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping(value = "/fund/balance/download", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "SchemeFundBalanceAPI", shortName = ApiShortNames.SCHEME_FUND_BALANCE)
    public ResponseEntity<Resource> downloadSchemeFundBalance(
            @Valid @RequestBody SchemeFundBalanceRequest request) throws IOException {

        MDC.put("apiName", "SchemeFundBalanceAPI");
        byte[] pdfBytes = schemeService.generateSchemeFundBalance(request);

        ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"scheme-fund-balance-"
                                + request.schemeNumber() + "-"
                                +  ".pdf\"")
                .contentLength(pdfBytes.length)
                .body(pdfResource);
    }

    @PostMapping("/profile/information")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "Scheme Profile Information API", shortName = ApiShortNames.SCHEME_PROFILE_INFORMATION)
    public ResponseEntity<ApiResponse<SchemeProfileInformationResponse>> getSchemeProfileInformation(
            @Valid @RequestBody SchemeProfileInformationRequest schemeProfileInformationRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "Scheme Profile Information API");
        SchemeProfileInformationResponse response = schemeService.getSchemeProfileInformation(schemeProfileInformationRequest);
        ApiResponse<SchemeProfileInformationResponse> body = responseBuilder.success(request, "Scheme Profile Information generated",
                response);

        return ResponseEntity.ok(body);
    }
       @PostMapping("/profile")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "Scheme Profile API", shortName = ApiShortNames.SCHEME_PROFILE)
    public ResponseEntity<ApiResponse<SchemeProfileResponse>> getSchemeProfile(
            @Valid @RequestBody SchemeProfileRequest request,
            HttpServletRequest httpRequest) {

        MDC.put("apiName", "Scheme Profile API");
        SchemeProfileResponse data = schemeService.getSchemeProfile(request);
        ApiResponse<SchemeProfileResponse> body = responseBuilder.success(httpRequest,
                "Scheme Profile retrieved successfully", data);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/member/details")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "Scheme Member Details API", shortName = ApiShortNames.SCHEME_MEMBER_DETAILS)
    public ResponseEntity<ApiResponse<SchemeMemberDetailsResponse>> getSchemeMemberDetails(
            @Valid @RequestBody SchemeMemberDetailsRequest request,
            HttpServletRequest httpRequest) {

        MDC.put("apiName", "Scheme Member Details API");
        SchemeMemberDetailsResponse data = schemeService.getSchemeMemberDetails(request);
        ApiResponse<SchemeMemberDetailsResponse> body = responseBuilder.success(httpRequest,
                "Scheme Member Details retrieved successfully", data);
        return ResponseEntity.ok(body);
    }
}
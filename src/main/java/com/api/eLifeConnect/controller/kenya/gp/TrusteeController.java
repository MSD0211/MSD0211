package com.api.elifeconnect.controller.kenya.gp;

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
import com.api.elifeconnect.service.gp.TrusteeService;
import com.api.elifeconnect.dto.gp.trustee.TrusteeDetailsRequest;
import com.api.elifeconnect.dto.gp.trustee.TrusteeDetailsResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/kenya/gp/trustee")
public class TrusteeController {

    private final TrusteeService trusteeService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public TrusteeController(TrusteeService trusteeService) {
        this.trusteeService = trusteeService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/details")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "Trustee Details API", shortName = ApiShortNames.MEMBER_DETAILS)
    public ResponseEntity<ApiResponse<TrusteeDetailsResponse>> getMemberDetails(
            @Valid @RequestBody TrusteeDetailsRequest request,
            HttpServletRequest httpRequest) {

        MDC.put("apiName", "Trustee Details API");
        TrusteeDetailsResponse data = trusteeService.getTrusteeDetails(request);
        ApiResponse<TrusteeDetailsResponse> body = responseBuilder.success(httpRequest,
                "Trustee Details retrieved successfully", data);
        return ResponseEntity.ok(body);
    }

}

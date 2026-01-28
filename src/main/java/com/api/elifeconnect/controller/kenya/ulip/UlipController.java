package com.api.elifeconnect.controller.kenya.ulip;


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
import com.api.elifeconnect.dto.ulip.fund.UlipFundPositionSingleRequest;
import com.api.elifeconnect.dto.ulip.fund.UlipFundPositionSingleResponse;
import com.api.elifeconnect.service.ulip.UlipService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/kenya/ulip")
public class UlipController {

    private final UlipService ulipService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public UlipController(UlipService ulipService) {
        this.ulipService = ulipService;
        this.responseBuilder = new ApiResponseBuilder();
    }


    @PostMapping("/fund/position/single")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("Ulip Fund Position Single API")
    public ResponseEntity<ApiResponse<UlipFundPositionSingleResponse>> getFundPositionSingle(
            @RequestBody UlipFundPositionSingleRequest ulipFundPositionSingleRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "Ulip Fund Position Single API");
        UlipFundPositionSingleResponse response = ulipService.getFundPositionSingle(ulipFundPositionSingleRequest);
        System.out.println("RESPONSE ::"+response.policyNumber());
        System.out.println("RESPONSE::"+response.fundAsOn());
        ApiResponse<UlipFundPositionSingleResponse> body =
                responseBuilder.success(request, "Ulip Fund Position Single done", response);

        return ResponseEntity.ok(body);
    }
}

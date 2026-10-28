package com.api.elifeconnect.controller.kenya.ulip;

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
import com.api.elifeconnect.dto.ulip.fund.UlipFundPositionSingleRequest;
import com.api.elifeconnect.dto.ulip.fund.UlipFundPositionSingleResponse;
import com.api.elifeconnect.service.ulip.UlipService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequestMapping("/api/v1/kenya/ulip")
@Tag(name = "ULIP Services", description = "Endpoints for Kenya ULIP fund position operations")
public class UlipController {

    private final UlipService ulipService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public UlipController(UlipService ulipService) {
        this.ulipService = ulipService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/fund/position/single")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "Ulip Fund Position Single API", shortName = ApiShortNames.ULIP_FUND_POSITION)
    @Operation(summary = "Get Single Fund Position", description = "Retrieves fund position and NAV details for a single ULIP policy.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "ULIP fund position retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<UlipFundPositionSingleResponse>> getFundPositionSingle(
            @Valid @RequestBody UlipFundPositionSingleRequest ulipFundPositionSingleRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "Ulip Fund Position Single API");
        UlipFundPositionSingleResponse response = ulipService.getFundPositionSingle(ulipFundPositionSingleRequest);
        System.out.println("RESPONSE ::" + response.policyNumber());
        System.out.println("RESPONSE::" + response.fundAsOn());
        ApiResponse<UlipFundPositionSingleResponse> body = responseBuilder.success(request,
                "Ulip Fund Position Single done", response);

        return ResponseEntity.ok(body);
    }
}

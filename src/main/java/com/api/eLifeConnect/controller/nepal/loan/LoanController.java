package com.api.elifeconnect.controller.nepal.loan;

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
import com.api.elifeconnect.dto.loan.LoanEnquiryRequest;
import com.api.elifeconnect.dto.loan.LoanEnquiryResponse;
import com.api.elifeconnect.dto.loan.LoanRepaymentRequest;
import com.api.elifeconnect.dto.loan.LoanRepaymentResponse;
import com.api.elifeconnect.service.loan.LoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController("nepalLoanController")
@RequestMapping("/api/v1/nepal/loan")
@Tag(name = "Loan Services", description = "Endpoints for Nepal loan enquiry and repayment operations")
public class LoanController {

    private final LoanService loanService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/enquiry")
    @PreAuthorize("hasAuthority('nepal.api.read')")
    @LogApiCall(value = "LoanEnquiryAPI", shortName = ApiShortNames.LOAN_ENQUIRY)
    @Operation(summary = "Loan Enquiry", description = "Retrieves loan eligibility and outstanding balance for Nepal policy.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Loan enquiry successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<LoanEnquiryResponse>> loanEnquiry(
            @Valid @RequestBody LoanEnquiryRequest req, HttpServletRequest request) {
        MDC.put("apiName", "LoanEnquiryAPI");
        LoanEnquiryResponse response = loanService.loanEnquiry(req);
        return ResponseEntity.ok(responseBuilder.success(request, "Loan enquiry success", response));
    }

    @PostMapping("/repayment")
    @PreAuthorize("hasAuthority('nepal.api.read')")
    @LogApiCall(value = "LoanRepaymentAPI", shortName = ApiShortNames.LOAN_REPAYMENT)
    @Operation(summary = "Loan Repayment", description = "Processes loan repayment for Nepal policy.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Loan repayment processed successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<LoanRepaymentResponse>> loanRepayment(
            @Valid @RequestBody LoanRepaymentRequest req, HttpServletRequest request) {
        MDC.put("apiName", "LoanRepaymentAPI");
        LoanRepaymentResponse response = loanService.loanRepayment(req);
        return ResponseEntity.ok(responseBuilder.success(request, "Loan repayment success", response));
    }
}

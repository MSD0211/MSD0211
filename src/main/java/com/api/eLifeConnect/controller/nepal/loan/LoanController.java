package com.api.elifeconnect.controller.nepal.loan;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.api.elifeconnect.aop.ApiShortNames;
import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.loan.*;
import com.api.elifeconnect.service.loan.LoanService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController("nepalLoanController")
@RequestMapping("/api/v1/nepal/loan")
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
    public ResponseEntity<ApiResponse<LoanEnquiryResponse>> loanEnquiry(
            @Valid @RequestBody LoanEnquiryRequest req, HttpServletRequest request) {
        MDC.put("apiName", "LoanEnquiryAPI");
        LoanEnquiryResponse response = loanService.loanEnquiry(req);
        return ResponseEntity.ok(responseBuilder.success(request, "Loan enquiry success", response));
    }

    @PostMapping("/repayment")
    @PreAuthorize("hasAuthority('nepal.api.read')")
    @LogApiCall(value = "LoanRepaymentAPI", shortName = ApiShortNames.LOAN_REPAYMENT)
    public ResponseEntity<ApiResponse<LoanRepaymentResponse>> loanRepayment(
            @Valid @RequestBody LoanRepaymentRequest req, HttpServletRequest request) {
        MDC.put("apiName", "LoanRepaymentAPI");
        LoanRepaymentResponse response = loanService.loanRepayment(req);
        return ResponseEntity.ok(responseBuilder.success(request, "Loan repayment success", response));
    }
}

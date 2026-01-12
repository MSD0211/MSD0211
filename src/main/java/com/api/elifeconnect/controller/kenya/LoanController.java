package com.api.elifeconnect.controller.kenya;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.loan.LoanEnquiryRequest;
import com.api.elifeconnect.dto.loan.LoanEnquiryResponse;
import com.api.elifeconnect.service.LoanService;

import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

    private final LoanService loanService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @GetMapping("/enquiry")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("LoanEnquiryAPI")
    public ResponseEntity<ApiResponse<LoanEnquiryResponse>> loanEnquiry(
            @RequestBody LoanEnquiryRequest loanEnqRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "LoanEnquiryAPI");
        LoanEnquiryResponse response = loanService.loanEnquiry(loanEnqRequest);
        System.out.println("RESPONSE ::"+response.httpStatus());
        System.out.println("RESPONSE::"+response.loanAmount());
        ApiResponse<LoanEnquiryResponse> body =
                responseBuilder.success(request, "Loan enquiry success", response);

        return ResponseEntity.ok(body);
    }
}

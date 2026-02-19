package com.api.elifeconnect.controller.kenya.loan;

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
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.loan.LoanQuotationRequest;
import com.api.elifeconnect.dto.loan.LoanRepaymentLetterRequest;
import com.api.elifeconnect.service.loan.LoanService;

@RestController
@RequestMapping("/api/v1/kenya/loan")
public class LoanController {

    private final LoanService loanService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    // @GetMapping("/enquiry")
    // @PreAuthorize("hasAuthority('kenya.api.read')")
    // @LogApiCall("LoanEnquiryAPI")
    // public ResponseEntity<ApiResponse<LoanEnquiryResponse>> loanEnquiry(
    //         @RequestBody LoanEnquiryRequest loanEnqRequest,
    //         HttpServletRequest request) {
                
    //     MDC.put("apiName", "LoanEnquiryAPI");
    //     LoanEnquiryResponse response = loanService.loanEnquiry(loanEnqRequest);
    //     System.out.println("RESPONSE ::"+response.httpStatus());
    //     System.out.println("RESPONSE::"+response.loanAmount());
    //     ApiResponse<LoanEnquiryResponse> body =
    //             responseBuilder.success(request, "Loan enquiry success", response);

    //     return ResponseEntity.ok(body);
    // }

    @PostMapping(
            value = "/quotation/download",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall("LoanQuotationAPI")
    public ResponseEntity<Resource> downloadLoanQuotationPdf(
            @Valid @RequestBody LoanQuotationRequest request
    ) throws IOException {

        MDC.put("apiName", "LoanQuotationAPI");
        byte[] pdfBytes = loanService .generateLoanQuotation(request);

        ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"loan-quotation-" 
                                + request.policyNumber() + ".pdf\""
                )
                .contentLength(pdfBytes.length)
                .body(pdfResource);
    }

    @PostMapping(
            value = "/repayment/letter/download",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall("LoanRepaymentLetterAPI")
    public ResponseEntity<Resource> downloadLoanRepaymentLetterPdf(
            @Valid @RequestBody LoanRepaymentLetterRequest request
    ) throws IOException {

        MDC.put("apiName", "LoanRepaymentLetterAPI");
        byte[] pdfBytes = loanService .generateLoanRepaymentLetter(request);

        ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"loan-repayment-letter-" 
                                + request.policyNumber() + ".pdf\""
                )
                .contentLength(pdfBytes.length)
                .body(pdfResource);
    }
}

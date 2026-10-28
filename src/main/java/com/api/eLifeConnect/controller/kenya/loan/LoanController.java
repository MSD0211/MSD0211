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
 
import com.api.elifeconnect.aop.ApiShortNames;
import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.loan.LoanQuotationRequest;
import com.api.elifeconnect.dto.loan.LoanRepaymentLetterRequest;
import com.api.elifeconnect.service.loan.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/kenya/loan")
@Tag(name = "Loan Services", description = "Endpoints for Kenya loan quotation and repayment letter downloads")
public class LoanController {

        private final LoanService loanService;
        private final ApiResponseBuilder responseBuilder;

        @Autowired
        public LoanController(LoanService loanService) {
                this.loanService = loanService;
                this.responseBuilder = new ApiResponseBuilder();
        }

        @PostMapping(value = "/quotation/download", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "LoanQuotationAPI", shortName = ApiShortNames.LOAN_QUOTATION)
        @Operation(summary = "Download Loan Quotation PDF", description = "Generates and downloads the loan quotation PDF for a policy.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Loan quotation PDF generated successfully"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<Resource> downloadLoanQuotationPdf(
                        @Valid @RequestBody LoanQuotationRequest request) throws IOException {

                MDC.put("apiName", "LoanQuotationAPI");
                byte[] pdfBytes = loanService.generateLoanQuotation(request);

                ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

                return ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_PDF)
                                .header(
                                                HttpHeaders.CONTENT_DISPOSITION,
                                                "attachment; filename=\"loan-quotation-"
                                                                + request.policyNumber() + ".pdf\"")
                                .contentLength(pdfBytes.length)
                                .body(pdfResource);
        }

        @PostMapping(value = "/repayment/letter/download", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "LoanRepaymentLetterAPI", shortName = ApiShortNames.LOAN_REPAYMENT_LETTER)
        @Operation(summary = "Download Loan Repayment Letter PDF", description = "Generates and downloads the loan repayment letter PDF for a policy.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Loan repayment letter PDF generated successfully"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<Resource> downloadLoanRepaymentLetterPdf(
                        @Valid @RequestBody LoanRepaymentLetterRequest request) throws IOException {

                MDC.put("apiName", "LoanRepaymentLetterAPI");
                byte[] pdfBytes = loanService.generateLoanRepaymentLetter(request);

                ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

                return ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_PDF)
                                .header(
                                                HttpHeaders.CONTENT_DISPOSITION,
                                                "attachment; filename=\"loan-repayment-letter-"
                                                                + request.policyNumber() + ".pdf\"")
                                .contentLength(pdfBytes.length)
                                .body(pdfResource);
        }
}

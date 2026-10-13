package com.api.elifeconnect.service.loan;

import com.api.elifeconnect.dto.loan.LoanQuotationRequest;
import com.api.elifeconnect.dto.loan.LoanRepaymentLetterRequest;

import com.api.elifeconnect.dto.loan.LoanEnquiryRequest;
import com.api.elifeconnect.dto.loan.LoanEnquiryResponse;
import com.api.elifeconnect.dto.loan.LoanRepaymentRequest;
import com.api.elifeconnect.dto.loan.LoanRepaymentResponse;

public interface LoanService {

    byte[] generateLoanQuotation(LoanQuotationRequest loanQuotationRequest);

    byte[] generateLoanRepaymentLetter(LoanRepaymentLetterRequest loanRepaymentLetterRequest);

    LoanEnquiryResponse loanEnquiry(LoanEnquiryRequest req);

    LoanRepaymentResponse loanRepayment(LoanRepaymentRequest req);

}

package com.api.elifeconnect.service.loan;

import com.api.elifeconnect.dto.loan.LoanQuotationRequest;
import com.api.elifeconnect.dto.loan.LoanRepaymentLetterRequest;

public interface LoanService {
          
    byte[] generateLoanQuotation(LoanQuotationRequest loanQuotationRequest);
    byte[] generateLoanRepaymentLetter(LoanRepaymentLetterRequest loanRepaymentLetterRequest);
    
}


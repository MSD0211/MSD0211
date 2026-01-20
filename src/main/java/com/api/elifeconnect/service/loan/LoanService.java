package com.api.elifeconnect.service.loan;

import com.api.elifeconnect.dto.loan.LoanQuotationRequest;

public interface LoanService {
          
    byte[] generateLoanQuotation (LoanQuotationRequest loanQuotationRequest);
}

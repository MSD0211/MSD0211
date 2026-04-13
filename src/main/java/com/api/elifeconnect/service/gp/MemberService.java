package com.api.elifeconnect.service.gp;

import com.api.elifeconnect.dto.loan.LoanQuotationRequest;
import com.api.elifeconnect.dto.loan.LoanRepaymentLetterRequest;

public interface MemberService {
          
    byte[] generateLoanQuotation(LoanQuotationRequest loanQuotationRequest);
    byte[] generateLoanRepaymentLetter(LoanRepaymentLetterRequest loanRepaymentLetterRequest);
    
}


package com.api.elifeconnect.service;

import com.api.elifeconnect.dto.loan.LoanEnquiryRequest;
import com.api.elifeconnect.dto.loan.LoanEnquiryResponse;

public interface LoanService {

    LoanEnquiryResponse loanEnquiry(LoanEnquiryRequest req);

}

package com.api.elifeconnect.service.proposal;

import com.api.elifeconnect.dto.proposal.ProposalPremiumEnquiryRequest;
import com.api.elifeconnect.dto.proposal.ProposalPremiumEnquiryResponse;
import com.api.elifeconnect.dto.proposal.ProposalSubmissionEnquiryRequest;
import com.api.elifeconnect.dto.proposal.ProposalSubmissionEnquiryResponse;

public interface ProposalService {

     ProposalPremiumEnquiryResponse proposalPremiumEnquiry(ProposalPremiumEnquiryRequest req);
     ProposalSubmissionEnquiryResponse proposalSubmissionEnquiry(ProposalSubmissionEnquiryRequest req);

}

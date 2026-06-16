package com.api.elifeconnect.service.policy;

import com.api.elifeconnect.dto.policy.AgentPolicyEnquiryRequest;
import com.api.elifeconnect.dto.policy.AgentPolicyEnquiryResponse;
import com.api.elifeconnect.dto.policy.CustomerPolicyEnquiryRequest;
import com.api.elifeconnect.dto.policy.CustomerPolicyEnquiryResponse;
import com.api.elifeconnect.dto.policy.PolicyDetailsRequest;
import com.api.elifeconnect.dto.policy.PolicyDetailsResponse;
import com.api.elifeconnect.dto.policy.PolicyRevivalQuotationRequest;
import com.api.elifeconnect.dto.policy.PolicyRevivalQuotationResponse;

public interface PolicyService {

    CustomerPolicyEnquiryResponse customerPolicyEnquiry(CustomerPolicyEnquiryRequest req);

    AgentPolicyEnquiryResponse agentPolicyEnquiry(AgentPolicyEnquiryRequest req);

    PolicyRevivalQuotationResponse fetchPolicyRevivalDetails(
            PolicyRevivalQuotationRequest policyRevivalQuotationRequest);

    PolicyDetailsResponse fetchPolicyDetails(PolicyDetailsRequest policyDetailsRequest);

}

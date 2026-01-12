package com.api.elifeconnect.service.policy;

import com.api.elifeconnect.dto.policy.AgentPolicyEnquiryRequest;
import com.api.elifeconnect.dto.policy.AgentPolicyEnquiryResponse;
import com.api.elifeconnect.dto.policy.CustomerPolicyEnquiryRequest;
import com.api.elifeconnect.dto.policy.CustomerPolicyEnquiryResponse;

public interface PolicyService {

    CustomerPolicyEnquiryResponse customerPolicyEnquiry(CustomerPolicyEnquiryRequest req);

    AgentPolicyEnquiryResponse agentPolicyEnquiry(AgentPolicyEnquiryRequest req);

}

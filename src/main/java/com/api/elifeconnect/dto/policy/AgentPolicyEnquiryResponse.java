package com.api.elifeconnect.dto.policy;

import java.util.List;

public record AgentPolicyEnquiryResponse(
        List<PolicyDetails> policyDetails,
        String message
) {}

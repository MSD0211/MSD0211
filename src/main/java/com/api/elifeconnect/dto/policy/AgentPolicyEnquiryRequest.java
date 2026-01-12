package com.api.elifeconnect.dto.policy;

public record AgentPolicyEnquiryRequest(
        String referenceId,
        String agentId
) {
}
package com.api.elifeconnect.dto.policy;

public record CustomerPolicyEnquiryRequest(
        String referenceId,
        String custId
) {
}
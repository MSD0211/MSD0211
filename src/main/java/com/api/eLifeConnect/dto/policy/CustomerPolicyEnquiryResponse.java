package com.api.elifeconnect.dto.policy;

import java.util.List;

public record CustomerPolicyEnquiryResponse(
        List<PolicyDetails> policyDetails,
        String message
) {}

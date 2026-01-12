package com.api.elifeconnect.dto.policy;

public record PolicyDetails(
        String policyNo,
        String name,
        String doc,
        String premiumAmount,
        String policyStatus
) {}

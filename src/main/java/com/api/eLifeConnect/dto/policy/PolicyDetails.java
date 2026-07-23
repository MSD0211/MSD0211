package com.api.elifeconnect.dto.policy;

public record PolicyDetails(
                String policyNo,
                String name,
                String doc,
                String fup,
                String premiumAmount,
                String policyStatus) {
}

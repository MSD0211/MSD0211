package com.api.elifeconnect.dto.plan.Details;

import java.util.List;

public record TermsAllowed(
        int policyTerm,
        List<Integer> premiumPayingTermAllowed
) {
    // Defensive copy to keep immutability (recommended)
    public TermsAllowed {
        premiumPayingTermAllowed = List.copyOf(premiumPayingTermAllowed);
    }
}


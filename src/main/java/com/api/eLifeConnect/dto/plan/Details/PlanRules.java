package com.api.elifeconnect.dto.plan.Details;

import java.math.BigDecimal;
import java.util.List;

public record PlanRules(
        List<String> policyModesAllowed,
        List<TermsAllowed> termsAllowed,
        BigDecimal minimumSumAssuredSinglePremium,
        BigDecimal maximumSumAssuredSinglePremium,
        BigDecimal minimumSumAssuredRegularPremium,
        BigDecimal maximumSumAssuredRegularPremium,
        BigDecimal minimumAbSumAssured,
        BigDecimal maximumAbSumAssured,
        BigDecimal minimumTrSumAssured,
        BigDecimal maximumTrSumAssured,
        BigDecimal minimumCirSumAssured,
        BigDecimal maximumCirSumAssured,
        int saMultiples
) {
    // Defensive copy to ensure immutability of lists
    public PlanRules {
        policyModesAllowed = List.copyOf(policyModesAllowed);
        termsAllowed = List.copyOf(termsAllowed);
    }
}

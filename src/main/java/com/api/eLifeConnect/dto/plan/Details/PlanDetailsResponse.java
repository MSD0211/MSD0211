package com.api.elifeconnect.dto.plan.Details;

import java.util.List;

public record PlanDetailsResponse(
        List<PlanDetails> plans,
        String message
) {
    public PlanDetailsResponse {
        plans = List.copyOf(plans); // make immutable copy
    }
}

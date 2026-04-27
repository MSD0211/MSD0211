package com.api.elifeconnect.dto.plan.Details;

import java.util.List;

public record PlanDetails(
        int plan,
        String planName,
        int age,
        List<PlanRules> planDetails
) {}

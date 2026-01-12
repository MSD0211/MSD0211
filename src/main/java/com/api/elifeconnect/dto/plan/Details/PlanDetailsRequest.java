package com.api.elifeconnect.dto.plan.Details;

public record PlanDetailsRequest(
        String referenceId,
        String dob,
        String doc,
        String gender,
        String ageProof
) {}

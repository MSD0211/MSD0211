package com.api.elifeconnect.dto.proposal.submit;

public record MerDetails(
        String bp,
        String abdomen,
        String pulse_rate,
        String chest_expiration,
        String chest_inspiration
) {}

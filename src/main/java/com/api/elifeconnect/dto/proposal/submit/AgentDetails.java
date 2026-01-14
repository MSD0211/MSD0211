package com.api.elifeconnect.dto.proposal.submit;

public record AgentDetails(
        String code,
        String name,
        String type,
        String email,
        String mobile_no
) {}

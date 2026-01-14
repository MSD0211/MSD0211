package com.api.elifeconnect.dto.proposal.submit;

public record BankDetails(
        String name,
        String branch,
        String account_number,
        String routing_number
) {}

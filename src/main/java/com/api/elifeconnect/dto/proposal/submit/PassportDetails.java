package com.api.elifeconnect.dto.proposal.submit;

public record PassportDetails(
        String date_of_issue,
        String date_of_expiry,
        String passport_number,
        String country_of_issue
) {}

package com.api.elifeconnect.dto.gp.scheme;

public record SchemeDetailsResponse(

        String schemeNumber,
        String schemeName,
        String schemeType,
        Long startDate,
        String fundManager,
        String fundAdministrator,
        Long renewalDate,
        Long lastContributionDate,
        String staffAdmins,
        String agentCode,
        String retirementPolicy,
        String employerPercentage,
        String employeePercentage,
        String investmentType,
        String rbaCertNo,
        String kraCertNo,
        String totalFundValue

) {
}
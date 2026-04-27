package com.api.elifeconnect.dto.gp.scheme;

public record SchemeProfileResponse(

        String schemeType,
        String schemeNumber,
        String employerPercentage,
        String employeePercentage,
        String investmentType,
        String rbaCertNo,
        String kraCertNo,
        String totalFundValue

) {}

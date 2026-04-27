package com.api.elifeconnect.dto.gp.scheme;

public record SchemeProfileResponse(

        String referenceId,
        String schemeType,
        String schemeNumber,
        String employerAge,
        String employeeAge,
        String investmentType,
        String rbaCertNo,
        String kraCertNo,
        String totalFundValue

) {}
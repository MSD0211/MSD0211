package com.api.elifeconnect.dto.policy;

public record PolicyDetailsResponse(

        String policyNo,
        String name,
        String startDate,
        String maturityDate,
        String sumAssured,
        String premiumDueDate,
        String premiumAmount,
        String polMode,
        String policyterm,
        String lastTrnDt,
        String branchCode,
        String agcode,
        String nomineeName,
        String vestedBonus,
        String planName,
        String policyStatus

) {
}
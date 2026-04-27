package com.api.elifeconnect.dto.gp.member;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public record MemberDetailsResponse(

        String referenceId,
        String name,
        Date dateJoined,
        BigDecimal fundValue,
        String memberStatus,
        String kyc,
        List<String> beneficiaryNames

) {}

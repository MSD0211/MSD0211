package com.api.elifeconnect.dto.gp.scheme;

import java.util.Date;

public record SchemeProfileInformationResponse(

    String schemeName,
    String fundManager,
    String fundAdministrator,
    Date startDate,
    String schemeType,
    Date renewalDate,
    Date lastContributionDate,
    String staffAdmins,
    String agentCode,
    String retirementPolicy

) {}
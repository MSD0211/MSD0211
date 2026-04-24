package com.api.elifeconnect.service.gp;

import com.api.elifeconnect.dto.gp.scheme.SchemeFundBalanceRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationResponse;

public interface SchemeService {
    byte[] generateSchemeFundBalance(SchemeFundBalanceRequest request);
    SchemeProfileInformationResponse getSchemeProfileInformation(SchemeProfileInformationRequest request);
}


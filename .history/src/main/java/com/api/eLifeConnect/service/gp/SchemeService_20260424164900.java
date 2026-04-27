package com.api.elifeconnect.service.gp;

import com.api.elifeconnect.dto.gp.scheme.SchemeFundBalanceRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsResponse;

public interface SchemeService {
    byte[] generateSchemeFundBalance(SchemeFundBalanceRequest request);
    SchemeProfileInformationResponse getSchemeProfileInformation(SchemeProfileInformationRequest request);
    SchemeProfileResponse getSchemeProfile(SchemeProfileRequest request);
    SchemeMemberDetailsResponse getSchemeMemberDetails(SchemeMemberDetailsRequest request);
}


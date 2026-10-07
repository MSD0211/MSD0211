package com.api.elifeconnect.service.gp;

import com.api.elifeconnect.dto.gp.scheme.AdminSchemesRequest;
import com.api.elifeconnect.dto.gp.scheme.AdminSchemesResponse;
import com.api.elifeconnect.dto.gp.scheme.PensionerSchemesRequest;
import com.api.elifeconnect.dto.gp.scheme.PensionerSchemesResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeDetailsRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeDetailsResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeFundBalanceRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileInformationResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeProfileResponse;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsRequest;
import com.api.elifeconnect.dto.gp.scheme.SchemeMemberDetailsResponse;
import com.api.elifeconnect.dto.gp.scheme.NationalIdSchemesRequest;
import com.api.elifeconnect.dto.gp.scheme.NationalIdSchemesResponse;

public interface SchemeService {
    byte[] generateSchemeFundBalance(SchemeFundBalanceRequest request);

    SchemeProfileInformationResponse getSchemeProfileInformation(SchemeProfileInformationRequest request);

    SchemeProfileResponse getSchemeProfile(SchemeProfileRequest request);

    SchemeMemberDetailsResponse getSchemeMemberDetails(SchemeMemberDetailsRequest request);

    SchemeDetailsResponse getSchemeDetails(SchemeDetailsRequest request);

    PensionerSchemesResponse getPensionerSchemes(PensionerSchemesRequest request);

    AdminSchemesResponse getAdminSchemes(AdminSchemesRequest request);

    NationalIdSchemesResponse getPensionerSchemesByNationalId(NationalIdSchemesRequest request);
}

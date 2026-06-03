package com.api.elifeconnect.service.gp;

import com.api.elifeconnect.dto.gp.trustee.TrusteeDetailsRequest;
import com.api.elifeconnect.dto.gp.trustee.TrusteeDetailsResponse;

public interface TrusteeService {

    TrusteeDetailsResponse getTrusteeDetails(TrusteeDetailsRequest request);

}


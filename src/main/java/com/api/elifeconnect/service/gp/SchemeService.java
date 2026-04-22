package com.api.elifeconnect.service.gp;

import com.api.elifeconnect.dto.gp.scheme.SchemeFundBalanceRequest;

public interface SchemeService {
    byte[] generateSchemeFundBalance(SchemeFundBalanceRequest request);
}


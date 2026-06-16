package com.api.elifeconnect.dto.gp.trustee;

import java.util.List;

public record TrusteeDetailsResponse(
        String name,
        String mobile,
        // String nationalId,
        String dob,
        String status,
        List<AddressDetails> addresses
) {
}
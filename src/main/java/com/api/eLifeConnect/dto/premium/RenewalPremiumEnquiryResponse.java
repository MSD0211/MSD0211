package com.api.elifeconnect.dto.premium;


public record RenewalPremiumEnquiryResponse(
        int httpStatus,
        PolicyDetails policy_details,
        String message,
        String status
) {
}

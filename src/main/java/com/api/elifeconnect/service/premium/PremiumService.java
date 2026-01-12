package com.api.elifeconnect.service.premium;

import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentResponse;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryResponse;

public interface PremiumService {

    RenewalPremiumEnquiryResponse renewalPremiumEnquiry(RenewalPremiumEnquiryRequest req);

    RenewalPremiumAdjustmentResponse renewalPremiumAdjustment(RenewalPremiumAdjustmentRequest req);

}

package com.api.elifeconnect.service.premium;

import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumAdjustmentResponse;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryRequest;
import com.api.elifeconnect.dto.premium.RenewalPremiumEnquiryResponse;
import com.api.elifeconnect.dto.premium.statement.PremiumStatementFullRequest;
import com.api.elifeconnect.dto.premium.statement.PremiumStatementFullResponse;
import com.api.elifeconnect.dto.premium.summary.PremiumSummaryRequest;
import com.api.elifeconnect.dto.premium.summary.PremiumSummaryResponse;

public interface PremiumService {

    RenewalPremiumEnquiryResponse renewalPremiumEnquiry(RenewalPremiumEnquiryRequest req);

    RenewalPremiumAdjustmentResponse renewalPremiumAdjustment(RenewalPremiumAdjustmentRequest req);

    PremiumStatementFullResponse premiumStatementFull(PremiumStatementFullRequest req);

    PremiumSummaryResponse premiumStatementFull(PremiumSummaryRequest req);

}

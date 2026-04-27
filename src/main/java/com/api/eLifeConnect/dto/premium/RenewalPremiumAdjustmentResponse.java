package com.api.elifeconnect.dto.premium;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RenewalPremiumAdjustmentResponse(

        String fup,

        String referenceNo,

        int httpStatus,

        String message,

        @JsonProperty("transaction_no")
        String transactionNo,

        String timestamp
) {
}

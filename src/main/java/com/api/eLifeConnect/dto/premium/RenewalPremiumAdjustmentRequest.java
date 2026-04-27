package com.api.elifeconnect.dto.premium;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RenewalPremiumAdjustmentRequest(

        /* Policy Number – Mandatory */
        @JsonProperty("policy_no")
        @NotBlank(message = "policyNo is required")
        @Size(max = 9)
        String policyNo,

        /* Amount – Mandatory, Decimal */
        @NotBlank(message = "amount is required")
        @Pattern(
            regexp = "\\d{1,12}(\\.\\d{1,2})?",
            message = "amount must be numeric (decimal allowed)"
        )
        String amount,

        /* Phone – Mandatory, Numeric */
        @NotBlank(message = "phone is required")
        @Pattern(regexp = "\\d+", message = "phone must be numeric")
        @Size(max = 15)
        String phone,

        /* Name – Mandatory */
        @NotBlank(message = "name is required")
        @Size(max = 100)
        String name,

        /* Short Code – OPTIONAL (default = 1) */
        @Pattern(regexp = "\\d*", message = "shortCode must be numeric")
        @Size(max = 2)
        String shortCode,

        /* Date – Mandatory, YYYY-MM-DD */
        @NotBlank(message = "date is required")
        @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "date must be YYYY-MM-DD"
        )
        String date,

        /* Type – OPTIONAL (default = 1) */
        @Pattern(regexp = "\\d*", message = "type must be numeric")
        @Size(max = 2)
        String type,

        /* Reference Number – Mandatory */
        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        @JsonAlias("referenceId")
        String referenceNo
) {
        public RenewalPremiumAdjustmentRequest {
                shortCode = (shortCode == null || shortCode.isBlank()) ? "1" : shortCode;
                type = (type == null || type.isBlank()) ? "1" : type;
        }
}
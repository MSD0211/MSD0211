package com.api.elifeconnect.dto.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DueListRequest(

                @NotBlank(message = "referenceId is required")
                @Size(max = 30)
                String referenceId,

                @NotBlank(message = "agencyCode is required")
                @Size(max = 9)
                String agencyCode,

                @NotBlank(message = "dueMMYYYY is required")
                @Size(min = 6, max = 6, message = "dueMMYYYY must be exactly 6 characters (MMYYYY)")
                @Pattern(regexp = "\\d{6}", message = "dueMMYYYY must be numeric (MMYYYY)")
                String dueMMYYYY,

                @NotBlank(message = "listType is required")
                @Pattern(regexp = "FYRP|ALLRP", message = "listType must be FYRP or ALLRP")
                String listType
) {
}

package com.api.elifeconnect.dto.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LapseListRequest(

                @NotBlank(message = "referenceId is required")
                @Size(max = 30)
                String referenceId,

                @NotBlank(message = "agencyCode is required")
                @Size(max = 9)
                String agencyCode,

                @NotBlank(message = "listType is required")
                @Pattern(regexp = "FLAP|SLAP", message = "listType must be FLAP or SLAP")
                String listType
) {
}

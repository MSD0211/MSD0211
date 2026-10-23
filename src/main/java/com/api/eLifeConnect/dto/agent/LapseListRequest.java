package com.api.elifeconnect.dto.agent;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for agent lapse list")
public record LapseListRequest(

                @NotBlank(message = "referenceId is required")
                @Size(max = 30)
                @Schema(description = "Unique reference ID for tracing", example = "REF-20261016-005")
                String referenceId,

                @NotBlank(message = "agencyCode is required")
                @Size(max = 9)
                @Schema(description = "Agency code", example = "AGC001")
                String agencyCode,

                @NotBlank(message = "listType is required")
                @Pattern(regexp = "FLAP|SLAP", message = "listType must be FLAP or SLAP")
                @Schema(description = "List type (FLAP or SLAP)", example = "FLAP", allowableValues = {"FLAP", "SLAP"})
                String listType
) {
}

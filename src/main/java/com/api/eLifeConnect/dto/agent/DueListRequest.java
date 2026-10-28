package com.api.elifeconnect.dto.agent;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for agent due list")
public record DueListRequest(

                @NotBlank(message = "referenceId is required")
                @Size(max = 30)
                @Schema(description = "Unique reference ID for tracing", example = "REF-20261016-004")
                String referenceId,

                @NotBlank(message = "agencyCode is required")
                @Size(max = 9)
                @Schema(description = "Agency code", example = "AGC001")
                String agencyCode,

                @NotBlank(message = "dueMMYYYY is required")
                @Size(min = 6, max = 6, message = "dueMMYYYY must be exactly 6 characters (MMYYYY)")
                @Pattern(regexp = "\\d{6}", message = "dueMMYYYY must be numeric (MMYYYY)")
                @Schema(description = "Due date in MMYYYY format", example = "102026")
                String dueMMYYYY,

                @NotBlank(message = "listType is required")
                @Pattern(regexp = "FYRP|ALLRP", message = "listType must be FYRP or ALLRP")
                @Schema(description = "List type (FYRP or ALLRP)", example = "FYRP", allowableValues = {"FYRP", "ALLRP"})
                String listType
) {
}

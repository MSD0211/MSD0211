package com.api.elifeconnect.dto.gp.scheme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NationalIdSchemesRequest(

        @NotBlank(message = "Reference Id is required")
        @Size(max = 20, message = "Reference Id cannot exceed 20 characters")
        String referenceId,

        @NotBlank(message = "National Id is required")
        @Size(max = 20, message = "National Id cannot exceed 20 characters")
        String nationalId

) {
}
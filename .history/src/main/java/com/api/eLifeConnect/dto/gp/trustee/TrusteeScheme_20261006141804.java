package com.api.elifeconnect.dto.gp.trustee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TrusteeScheme(

        @NotBlank(message = "Scheme Id is required")
        @Size(max = 20, message = "Scheme Id cannot exceed 20 characters")
        String schemeId,

        @NotBlank(message = "Scheme Name is required")
        String schemeName,

        @NotBlank(message = "Scheme Status is required")
        String schemeStatus,

        @NotBlank(message = "Scheme Type is required")
        String schemeType

         Integer productId

) {
}
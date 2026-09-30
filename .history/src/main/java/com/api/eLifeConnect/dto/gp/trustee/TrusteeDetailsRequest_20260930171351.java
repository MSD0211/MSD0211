package com.api.elifeconnect.dto.gp.trustee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TrusteeDetailsRequest(

        @NotBlank(message = "Reference Id is required")
        @Size(max = 20, message = "Reference Id cannot exceed 20 characters")
        String referenceId,

        @NotBlank(message = "Trustee Email Id is required")
        @Email(message = "Invalid email format")
        String trusteeEmailId

) {
}

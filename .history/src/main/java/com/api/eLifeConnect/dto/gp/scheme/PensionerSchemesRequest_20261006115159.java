package com.api.elifeconnect.dto.gp.scheme;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PensionerSchemesRequest(

        @NotBlank(message = "Reference Id is required") @Size(max = 50, message = "Reference Id cannot exceed 50 characters") String referenceId,

        @NotBlank(message = "Email Id is required") @Email(message = "Invalid email format") @Size(max = 100, message = "Email Id cannot exceed 100 characters") String emailId

) {
}
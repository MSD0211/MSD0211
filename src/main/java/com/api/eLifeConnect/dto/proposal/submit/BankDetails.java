package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BankDetails(
        
        // @NotBlank(message = "Bank name is mandatory")
        @Size(max = 270)
        String name,

        // @NotBlank(message = "Bank branch is mandatory")
        @Size(max = 75)
        String branch,

        // @NotBlank(message = "Bank account number is mandatory")
        @Size(max = 75)
        String account_number,

        @Size(max = 75)
        String routing_number
) {}

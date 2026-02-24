package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record HusbandDetails(

        /* Name – Mandatory */
        @NotBlank(message = "name is required")
        @Size(max = 75)
        String name,

        /* Income – NOT mandatory, Numeric */
        @Pattern(regexp = "\\d*", message = "income must be numeric")
        @Size(max = 12)
        String income,

        /* Occupation – NOT mandatory */
        @Size(max = 60)
        String occupation
) {}
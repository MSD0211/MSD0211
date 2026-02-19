package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;

public record HusbandDetails(
        @NotBlank(message = "name is required")
        String name,

        String income,

        String occupation
) {}

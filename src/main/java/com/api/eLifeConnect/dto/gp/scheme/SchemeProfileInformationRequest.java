package com.api.elifeconnect.dto.gp.scheme;

import jakarta.validation.constraints.*;

public record SchemeProfileInformationRequest(

    @NotBlank(message = "Reference ID is required")
    String referenceId,

    @NotBlank(message = "Scheme Number is required")
    String schemeNumber

) {}
package com.api.elifeconnect.dto.premium.summary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDetails(

        @NotNull @NotBlank
        String addressLine1,

        @NotNull @NotBlank
        String addressLine2,

        @NotNull
        Integer pinCode
) {
}

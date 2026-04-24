
package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressOfCommunication(

        @NotBlank(message = "Address line1 is mandatory")
        @Size(max = 100)
        String line1,

        @NotBlank(message = "Address line2 is mandatory")
        @Size(max = 100)
        String line2,

        @NotBlank(message = "Address line3 is mandatory")
        @Size(max = 100)
        String line3,

        @NotBlank(message = "Address line4/Province is mandatory")
        @Size(max = 100)
        String line4,

        @NotBlank(message = "Mobile Number is mandatory")
        @Size(max = 20)
        @Pattern(regexp = "\\d+", message = "Mobile must be numeric")
        String mob,

        @NotNull(message = "Telephone number cannot be NULL !!")
        @Size(max = 20)
        String tel
) {}

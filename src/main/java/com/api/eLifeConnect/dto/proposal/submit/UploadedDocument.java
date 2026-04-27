package com.api.elifeconnect.dto.proposal.submit;

import jakarta.validation.constraints.NotBlank;

public record UploadedDocument(
        @NotBlank(message = "title is required")
        String title,

        @NotBlank(message = "filename is required")
        String filename,

        String subtitle
) {}

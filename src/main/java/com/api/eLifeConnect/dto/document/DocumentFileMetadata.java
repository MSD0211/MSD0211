package com.api.elifeconnect.dto.document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentFileMetadata {

    @NotBlank(message = "tag is required")
    private String tag;

    private String description;

    private String storedName; // Optional: custom name for storage
}

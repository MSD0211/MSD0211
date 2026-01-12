package com.api.elifeconnect.dto.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentFileMetadata {

    private String tag;
    private String description;
    private String storedName; // Optional: custom name for storage
}

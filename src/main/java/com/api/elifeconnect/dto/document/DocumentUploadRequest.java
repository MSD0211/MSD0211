package com.api.elifeconnect.dto.document;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentUploadRequest {
    @NotBlank(message = "referenceId is required")
    private String referenceId;

    @NotNull(message = "fileMetadataList is required")
    @Size(min = 1, message = "at least one file metadata entry is required")
    private List<@Valid DocumentFileMetadata> fileMetadataList;

    private Map<String, Object> additionalData;
}

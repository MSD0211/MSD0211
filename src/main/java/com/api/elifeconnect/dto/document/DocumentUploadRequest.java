package com.api.elifeconnect.dto.document;

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
    
    private String referenceId;
    private List<DocumentFileMetadata> fileMetadataList;
    private Map<String, Object> additionalData;
}

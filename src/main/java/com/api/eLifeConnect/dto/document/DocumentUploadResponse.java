package com.api.elifeconnect.dto.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentUploadResponse {
    
    private Long uploadId;
    private String referenceId;
    private int uploadedFileCount;
    private LocalDateTime uploadedAt;
    private String message;
}

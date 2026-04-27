package com.api.elifeconnect.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity to track document uploads to external APIs
 */
@Entity
@Table(name = "external_document_upload")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalDocumentUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "external_upload_id")
    private Long externalUploadId;

    @Column(name = "reference_id", length = 50)
    private String referenceId;

    @Column(name = "api_url", length = 500)
    private String apiUrl;

    @Column(name = "uploaded_by", length = 50)
    private String uploadedBy;

    @Column(name = "http_status")
    private Integer httpStatus;

    @Column(name = "success")
    private Boolean success;

    @Lob
    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "duration_ms")
    private Long durationMs;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "externalDocumentUpload", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExternalDocumentFile> externalDocumentFiles;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (uploadedAt == null) {
            uploadedAt = LocalDateTime.now();
        }
        if (startTime == null) {
            startTime = LocalDateTime.now();
        }
    }
}

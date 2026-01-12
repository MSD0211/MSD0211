package com.api.elifeconnect.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entity to track individual files sent to external APIs
 */
@Entity
@Table(name = "external_document_file")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalDocumentFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "external_document_id")
    private Long externalDocumentId;

    @Column(name = "external_upload_id", nullable = false, insertable = false, updatable = false)
    private Long externalUploadId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "external_upload_id", nullable = false, foreignKey = @ForeignKey(name = "fk_external_upload"))
    private ExternalDocumentUpload externalDocumentUpload;

    @Column(name = "file_name", length = 255)
    private String fileName;

    @Column(name = "original_name", length = 255)
    private String originalName;

    @Column(name = "stored_name", length = 255)
    private String storedName;

    @Column(name = "tag", length = 50)
    private String tag;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "content_type", length = 100)
    private String contentType;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "storage_path", length = 500)
    private String storagePath;

    @Column(name = "checksum", length = 64)
    private String checksum;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (sentAt == null) {
            sentAt = LocalDateTime.now();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}

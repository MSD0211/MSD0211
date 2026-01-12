package com.api.elifeconnect.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "document_file")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "upload_id", nullable = false, insertable = false, updatable = false)
    private Long uploadId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_id", nullable = false, foreignKey = @ForeignKey(name = "fk_upload"))
    private DocumentUpload documentUpload;

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
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (sentAt == null) {
            sentAt = LocalDateTime.now();
        }
    }
}

package com.api.elifeconnect.aop;

import com.api.elifeconnect.entity.DocumentFile;
import com.api.elifeconnect.entity.DocumentUpload;
import com.api.elifeconnect.repository.DocumentFileRepository;
import com.api.elifeconnect.repository.DocumentUploadRepository;
import com.api.elifeconnect.dto.document.DocumentFileMetadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class DocumentOperationLoggingAspect {

    private final DocumentUploadRepository documentUploadRepository;
    private final DocumentFileRepository documentFileRepository;
    private final ObjectMapper objectMapper;

    @Around("@annotation(logDocumentOperation)")
    public Object logDocumentOperation(ProceedingJoinPoint joinPoint,
            LogDocumentOperation logDocumentOperation) throws Throwable {

        String operationType = logDocumentOperation.operationType();
        long startTime = System.currentTimeMillis();

        log.info("📋 [{}] Document operation started", operationType);

        try {
            Object result = joinPoint.proceed();

            // Extract arguments for logging
            Object[] args = joinPoint.getArgs();

            if ("UPLOAD".equalsIgnoreCase(operationType)) {
                handleUploadLogging(args, startTime);
            } else if ("DOWNLOAD".equalsIgnoreCase(operationType)) {
                handleDownloadLogging(args, startTime);
            }

            return result;

        } catch (Exception ex) {
            log.error("❌ [{}] Document operation failed: {}", operationType, ex.getMessage(), ex);
            throw ex;
        }
    }

    private void handleUploadLogging(Object[] args, long startTime) {
        try {
            // Extract arguments: url, multipartData, referenceId, fileMetadata, headers,
            // responseType
            String url = args.length > 0 ? (String) args[0] : null;
            String referenceId = args.length > 2 ? (String) args[2] : null;
            @SuppressWarnings("unchecked")
            List<DocumentFileMetadata> fileMetadataList = args.length > 3 ? (List<DocumentFileMetadata>) args[3]
                    : new ArrayList<>();

            // Extract multipart data
            @SuppressWarnings("unchecked")
            MultiValueMap<String, HttpEntity<?>> multipartData = args.length > 1
                    ? (MultiValueMap<String, HttpEntity<?>>) args[1]
                    : null;

            // Extract uploaded_by from JWT
            String uploadedBy = extractUploadedByFromJwt();

            // Create DocumentUpload entry with new fields
            DocumentUpload documentUpload = DocumentUpload.builder()
                    .referenceId(referenceId)
                    .apiUrl(url)
                    .uploadedBy(uploadedBy)
                    .startTime(LocalDateTime.now())
                    .uploadedAt(LocalDateTime.now())
                    .build();

            documentUpload = documentUploadRepository.save(documentUpload);

            // Process each file
            List<DocumentFile> documentFiles = new ArrayList<>();

            if (multipartData != null && multipartData.containsKey("files")) {
                List<HttpEntity<?>> fileEntities = multipartData.get("files");

                for (int i = 0; i < fileEntities.size(); i++) {
                    HttpEntity<?> fileEntity = fileEntities.get(i);
                    DocumentFileMetadata metadata = i < fileMetadataList.size() ? fileMetadataList.get(i)
                            : new DocumentFileMetadata();

                    // Extract file information
                    String originalName = extractFileName(fileEntity);
                    String contentType = extractContentType(fileEntity);
                    Long fileSize = extractFileSize(fileEntity);
                    byte[] fileBytes = extractFileBytes(fileEntity);
                    String checksum = calculateChecksum(fileBytes);

                    // Create DocumentFile entry with new fields
                    DocumentFile documentFile = DocumentFile.builder()
                            .documentUpload(documentUpload)
                            .fileName(originalName) // NEW: same as originalName
                            .originalName(originalName)
                            .storedName(metadata.getStoredName() != null ? metadata.getStoredName() : originalName)
                            .tag(metadata.getTag())
                            .description(metadata.getDescription())
                            .contentType(contentType)
                            .fileSize(fileSize)
                            .checksum(checksum)
                            .sentAt(LocalDateTime.now()) // NEW
                            .createdAt(LocalDateTime.now())
                            .build();

                    documentFiles.add(documentFile);

                    // Log file details to console
                    log.info(
                            "📤 [UPLOAD] File: {} | Type: {} | Size: {} bytes | Tag: {} | Description: {} | Checksum: {}",
                            originalName, contentType, fileSize, metadata.getTag(), metadata.getDescription(),
                            checksum);
                }

                // Save all document files
                documentFileRepository.saveAll(documentFiles);
            }

            long duration = System.currentTimeMillis() - startTime;

            // Update upload record with success status
            documentUpload.setSuccess(true);
            documentUpload.setHttpStatus(200);
            documentUpload.setEndTime(LocalDateTime.now());
            documentUpload.setDurationMs(duration);
            documentUploadRepository.save(documentUpload);

            log.info("✅ [UPLOAD] Completed | Upload ID: {} | Reference: {} | Files: {} | Duration: {} ms",
                    documentUpload.getUploadId(), referenceId, documentFiles.size(), duration);

        } catch (Exception ex) {
            log.error("❌ [UPLOAD] Failed to log document upload: {}", ex.getMessage(), ex);
        }
    }

    private void handleDownloadLogging(Object[] args, long startTime) {
        try {
            // For download operations, log basic information
            log.info("📥 [DOWNLOAD] Document download operation");

            long duration = System.currentTimeMillis() - startTime;
            log.info("✅ [DOWNLOAD] Completed | Duration: {} ms", duration);

        } catch (Exception ex) {
            log.error("❌ [DOWNLOAD] Failed to log document download: {}", ex.getMessage(), ex);
        }
    }

    private String extractUploadedByFromJwt() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
                return jwt.getClaimAsString("client_id");
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private String extractFileName(HttpEntity<?> fileEntity) {
        try {
            String contentDisposition = fileEntity.getHeaders().getContentDisposition().toString();
            if (contentDisposition != null && contentDisposition.contains("filename=")) {
                String[] parts = contentDisposition.split("filename=");
                if (parts.length > 1) {
                    return parts[1].replaceAll("\"", "").trim();
                }
            }
        } catch (Exception ignored) {
        }
        return "unknown";
    }

    private String extractContentType(HttpEntity<?> fileEntity) {
        try {
            if (fileEntity.getHeaders().getContentType() != null) {
                return fileEntity.getHeaders().getContentType().toString();
            }
        } catch (Exception ignored) {
        }
        return "application/octet-stream";
    }

    private Long extractFileSize(HttpEntity<?> fileEntity) {
        try {
            Object body = fileEntity.getBody();
            if (body instanceof byte[] bytes) {
                return (long) bytes.length;
            } else if (body instanceof org.springframework.core.io.Resource resource) {
                return resource.contentLength();
            }
        } catch (Exception ignored) {
        }
        return 0L;
    }

    private byte[] extractFileBytes(HttpEntity<?> fileEntity) {
        try {
            Object body = fileEntity.getBody();
            if (body instanceof byte[] bytes) {
                return bytes;
            } else if (body instanceof org.springframework.core.io.Resource resource) {
                return resource.getContentAsByteArray();
            }
        } catch (Exception ignored) {
        }
        return new byte[0];
    }

    private String calculateChecksum(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(data);
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}

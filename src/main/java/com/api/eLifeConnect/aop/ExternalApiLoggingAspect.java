package com.api.elifeconnect.aop;

import com.api.elifeconnect.aop.LogExternalCall;
import com.api.elifeconnect.entity.ExternalDocumentFile;
import com.api.elifeconnect.entity.ExternalDocumentUpload;
import com.api.elifeconnect.repository.ExternalDocumentFileRepository;
import com.api.elifeconnect.repository.ExternalDocumentUploadRepository;
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
import reactor.core.publisher.Mono;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ExternalApiLoggingAspect {

        private final ExternalDocumentUploadRepository externalDocumentUploadRepository;
        private final ExternalDocumentFileRepository externalDocumentFileRepository;

        // ThreadLocal to store upload ID for async Mono callbacks
        private final ThreadLocal<Long> currentExternalUploadId = new ThreadLocal<>();

        @Around("@annotation(logExternalCall)")
        public Object logExternalApi(ProceedingJoinPoint joinPoint,
                        LogExternalCall logExternalCall) throws Throwable {

                long start = System.currentTimeMillis();

                String methodName = joinPoint.getSignature().toShortString();
                Object[] args = joinPoint.getArgs();
                boolean isDocumentUpload = false;

                // Check if this is a document upload call and log document details
                if ("UPLOAD PDF with JSON".equals(logExternalCall.value()) && args.length > 1
                                && args[1] instanceof MultiValueMap) {
                        logDocumentUploadDetails(args);
                        isDocumentUpload = true;
                } else {
                        log.info("➡️ [{}] Calling external API | Method: {} | Args: {}",
                                        logExternalCall.value(),
                                        methodName,
                                        Arrays.toString(args));
                }

                try {
                        Object result = joinPoint.proceed(); // call the method

                        // If the method returns a Mono (WebClient style)
                        if (result instanceof Mono<?> monoResult) {

                                if (isDocumentUpload) {
                                        return monoResult
                                                        .doOnSuccess(res -> {
                                                                log.info("✅ [{}] Success | Method: {} | Time: {} ms | Response: {}",
                                                                                logExternalCall.value(),
                                                                                methodName,
                                                                                System.currentTimeMillis() - start,
                                                                                res);
                                                                markExternalUploadAsSuccess(start);
                                                        })
                                                        .doOnError(ex -> {
                                                                log.error("❌ [{}] Error | Method: {} | Time: {} ms | Error: {}",
                                                                                logExternalCall.value(),
                                                                                methodName,
                                                                                System.currentTimeMillis() - start,
                                                                                ex.getMessage());
                                                                Long uploadId = currentExternalUploadId.get();
                                                                if (uploadId != null) {
                                                                        markExternalUploadAsFailed(uploadId,
                                                                                        ex.getMessage());
                                                                }
                                                        })
                                                        .doFinally(signal -> currentExternalUploadId.remove());
                                } else {
                                        return monoResult
                                                        .doOnSuccess(res -> log.info(
                                                                        "✅ [{}] Success | Method: {} | Time: {} ms | Response: {}",
                                                                        logExternalCall.value(),
                                                                        methodName,
                                                                        System.currentTimeMillis() - start,
                                                                        res))
                                                        .doOnError(ex -> log.error(
                                                                        "❌ [{}] Error | Method: {} | Time: {} ms | Error: {}",
                                                                        logExternalCall.value(),
                                                                        methodName,
                                                                        System.currentTimeMillis() - start,
                                                                        ex.getMessage()));
                                }
                        }

                        // If method is not reactive, simply return the result
                        log.info("✅ [{}] Success | Method: {} | Time: {} ms | Response: {}",
                                        logExternalCall.value(),
                                        methodName,
                                        System.currentTimeMillis() - start,
                                        result);

                        return result;

                } catch (Exception ex) {

                        log.error("❌ [{}] Exception in {} | Time: {} ms | Error: {}",
                                        logExternalCall.value(),
                                        methodName,
                                        System.currentTimeMillis() - start,
                                        ex.getMessage(), ex);

                        throw ex;
                }
        }

        /**
         * Logs detailed document information when uploading files to external API
         * and saves to database
         */
        private void logDocumentUploadDetails(Object[] args) {
                ExternalDocumentUpload externalUpload = null;

                try {
                        String url = args.length > 0 ? (String) args[0] : "unknown";
                        @SuppressWarnings("unchecked")
                        MultiValueMap<String, HttpEntity<?>> multipartData = (MultiValueMap<String, HttpEntity<?>>) args[1];

                        log.info("🌐 [EXTERNAL API] Uploading documents to: {}", url);

                        // Extract uploaded_by from JWT
                        String uploadedBy = extractUploadedByFromJwt();

                        // Extract metadata from multipart data
                        List<com.api.elifeconnect.dto.document.DocumentFileMetadata> fileMetadataList = extractMetadataFromMultipart(
                                        multipartData);

                        // Create ExternalDocumentUpload entry
                        externalUpload = ExternalDocumentUpload.builder()
                                        .apiUrl(url)
                                        .uploadedBy(uploadedBy)
                                        .startTime(LocalDateTime.now())
                                        .uploadedAt(LocalDateTime.now())
                                        .build();

                        externalUpload = externalDocumentUploadRepository.save(externalUpload);

                        // Store upload ID in ThreadLocal for later use in callbacks
                        currentExternalUploadId.set(externalUpload.getExternalUploadId());

                        if (multipartData != null && multipartData.containsKey("files")) {
                                List<HttpEntity<?>> fileEntities = multipartData.get("files");
                                log.info("📦 [EXTERNAL API] Total files to upload: {}", fileEntities.size());

                                List<ExternalDocumentFile> externalDocumentFiles = new ArrayList<>();

                                for (int i = 0; i < fileEntities.size(); i++) {
                                        HttpEntity<?> fileEntity = fileEntities.get(i);

                                        // Get metadata for this file index
                                        com.api.elifeconnect.dto.document.DocumentFileMetadata metadata = i < fileMetadataList
                                                        .size()
                                                                        ? fileMetadataList.get(i)
                                                                        : new com.api.elifeconnect.dto.document.DocumentFileMetadata();

                                        String fileName = extractFileName(fileEntity);
                                        String contentType = extractContentType(fileEntity);
                                        Long fileSize = extractFileSize(fileEntity);
                                        byte[] fileBytes = extractFileBytes(fileEntity);
                                        String checksum = calculateChecksum(fileBytes);

                                        // Create ExternalDocumentFile entry with metadata
                                        ExternalDocumentFile externalDocFile = ExternalDocumentFile.builder()
                                                        .externalDocumentUpload(externalUpload)
                                                        .fileName(fileName)
                                                        .originalName(fileName)
                                                        .storedName(metadata.getStoredName() != null
                                                                        ? metadata.getStoredName()
                                                                        : fileName)
                                                        .tag(metadata.getTag())
                                                        .description(metadata.getDescription())
                                                        .contentType(contentType)
                                                        .fileSize(fileSize)
                                                        .checksum(checksum)
                                                        .sentAt(LocalDateTime.now())
                                                        .createdAt(LocalDateTime.now())
                                                        .build();

                                        externalDocumentFiles.add(externalDocFile);

                                        log.info("📄 [EXTERNAL API] File #{} | Name: {} | Type: {} | Size: {} bytes | Tag: {} | Description: {} | Checksum: {}",
                                                        i + 1, fileName, contentType, fileSize, metadata.getTag(),
                                                        metadata.getDescription(), checksum);
                                }

                                // Save all external document files
                                externalDocumentFileRepository.saveAll(externalDocumentFiles);

                                log.info("💾 [EXTERNAL API] Saved {} files to database for external upload ID: {}",
                                                externalDocumentFiles.size(), externalUpload.getExternalUploadId());
                        }

                        // Log JSON data if present
                        if (multipartData != null && multipartData.containsKey("data")) {
                                log.info("📋 [EXTERNAL API] JSON data included in upload");
                        }

                        // Log metadata if present
                        if (multipartData != null && multipartData.containsKey("metadata")) {
                                log.info("📋 [EXTERNAL API] Metadata included in upload: {} file(s)",
                                                fileMetadataList.size());
                        }

                } catch (Exception ex) {
                        log.warn("⚠️ [EXTERNAL API] Could not extract/save document details: {}", ex.getMessage());

                        // Mark upload as failed if we created a record
                        if (externalUpload != null && externalUpload.getExternalUploadId() != null) {
                                markExternalUploadAsFailed(externalUpload.getExternalUploadId(), ex.getMessage());
                        }
                }
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
                        if (fileEntity.getHeaders() != null) {
                                var contentType = fileEntity.getHeaders().getContentType();
                                if (contentType != null) {
                                        return contentType.toString();
                                }
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

        private List<com.api.elifeconnect.dto.document.DocumentFileMetadata> extractMetadataFromMultipart(
                        MultiValueMap<String, HttpEntity<?>> multipartData) {
                try {
                        if (multipartData != null && multipartData.containsKey("metadata")) {
                                List<HttpEntity<?>> metadataEntities = multipartData.get("metadata");
                                if (!metadataEntities.isEmpty()) {
                                        HttpEntity<?> metadataEntity = metadataEntities.get(0);
                                        Object body = metadataEntity.getBody();

                                        if (body instanceof String metadataJson) {
                                                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                                                return mapper.readValue(metadataJson,
                                                                new com.fasterxml.jackson.core.type.TypeReference<List<com.api.elifeconnect.dto.document.DocumentFileMetadata>>() {
                                                                });
                                        }
                                }
                        }
                } catch (Exception e) {
                        log.warn("⚠️ [EXTERNAL API] Failed to extract metadata from multipart: {}", e.getMessage());
                }
                return new ArrayList<>();
        }

        private void markExternalUploadAsSuccess(long startTime) {
                try {
                        Long uploadId = currentExternalUploadId.get();
                        if (uploadId != null) {
                                externalDocumentUploadRepository.findById(uploadId).ifPresent(upload -> {
                                        upload.setSuccess(true);
                                        upload.setHttpStatus(200);
                                        upload.setEndTime(LocalDateTime.now());
                                        if (upload.getStartTime() != null) {
                                                upload.setDurationMs(
                                                                java.time.Duration
                                                                                .between(upload.getStartTime(),
                                                                                                upload.getEndTime())
                                                                                .toMillis());
                                        }
                                        externalDocumentUploadRepository.save(upload);
                                        log.info("💾 [EXTERNAL API] Marked upload ID {} as successful", uploadId);
                                });
                        }
                } catch (Exception ex) {
                        log.error("Failed to mark external upload as successful: {}", ex.getMessage());
                }
        }

        private void markExternalUploadAsFailed(Long externalUploadId, String errorMessage) {
                try {
                        externalDocumentUploadRepository.findById(externalUploadId).ifPresent(upload -> {
                                upload.setSuccess(false);
                                upload.setErrorMessage(errorMessage);
                                upload.setEndTime(LocalDateTime.now());
                                if (upload.getStartTime() != null) {
                                        upload.setDurationMs(
                                                        java.time.Duration
                                                                        .between(upload.getStartTime(),
                                                                                        upload.getEndTime())
                                                                        .toMillis());
                                }
                                externalDocumentUploadRepository.save(upload);
                        });
                } catch (Exception ex) {
                        log.error("Failed to mark external upload as failed: {}", ex.getMessage());
                }
        }
}

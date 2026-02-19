package com.api.elifeconnect.controller;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpEntity;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.service.GenericApiService;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/sample")
public class SampleDynamicController {

    private final GenericApiService apiService;

    public SampleDynamicController(GenericApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/dynamic")
    public Object callDynamicApi(@Valid @RequestBody SampleRequest request) {
        String url = "https://external-api.com/process";

        // Define mapping: Status Code -> Response Class
        Map<Integer, Class<?>> statusMapper = new HashMap<>();
        statusMapper.put(200, SuccessResponse.class);
        statusMapper.put(400, ErrorResponse.class);
        statusMapper.put(500, ErrorResponse.class);

        // Call the service
        return apiService.executeWithDynamicResponse(
                url,
                "POST",
                request,
                null, // headers
                statusMapper,
                Object.class // fallback
        );
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @com.api.elifeconnect.aop.LogApiCall("Document Upload API")
    public Object uploadFiles(
            @RequestPart("files") MultipartFile[] files,
            @RequestPart("data") String jsonString,
            @RequestPart(value = "metadata", required = false) String metadataJson,
            @RequestPart(value = "referenceId", required = false) String referenceId) throws JsonProcessingException {

        // Log incoming upload request
        System.out.println("📥 [/upload API] Upload request received");
        System.out.println("📦 [/upload API] Total files: " + files.length);

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            System.out.println(String.format("📄 [/upload API] File #%d | Name: %s | Type: %s | Size: %d bytes",
                    i + 1,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize()));
        }

        if (referenceId != null && !referenceId.isEmpty()) {
            System.out.println("🔖 [/upload API] Reference ID: " + referenceId);
        }

        String url = "https://external-api.com/upload";

        // Parse metadata JSON to List<DocumentFileMetadata>
        List<com.api.elifeconnect.dto.document.DocumentFileMetadata> fileMetadataList = new ArrayList<>();
        if (metadataJson != null && !metadataJson.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            fileMetadataList = mapper.readValue(metadataJson,
                    new com.fasterxml.jackson.core.type.TypeReference<List<com.api.elifeconnect.dto.document.DocumentFileMetadata>>() {
                    });
        }

        MultiValueMap<String, HttpEntity<?>> multipartData = new LinkedMultiValueMap<>();

        // 1. Add JSON part
        HttpHeaders jsonHeaders = new HttpHeaders();
        jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> jsonEntity = new HttpEntity<>(jsonString, jsonHeaders);
        multipartData.add("data", jsonEntity);

        // 2. Add Metadata part (if present)
        if (!fileMetadataList.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                String metadataJsonString = mapper.writeValueAsString(fileMetadataList);

                HttpHeaders metadataHeaders = new HttpHeaders();
                metadataHeaders.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> metadataEntity = new HttpEntity<>(metadataJsonString, metadataHeaders);
                multipartData.add("metadata", metadataEntity);

                System.out.println("📋 [/upload API] Added metadata to multipart request: " + fileMetadataList.size()
                        + " file(s)");
            } catch (JsonProcessingException e) {
                System.out.println("⚠️ [/upload API] Failed to serialize metadata: " + e.getMessage());
            }
        }

        // 3. Add Files
        for (MultipartFile file : files) {
            HttpHeaders fileHeaders = new HttpHeaders();
            fileHeaders.setContentType(MediaType.parseMediaType(file.getContentType()));
            // Important: Set filename in content-disposition
            fileHeaders.setContentDispositionFormData("files", file.getOriginalFilename());

            try {
                // Use ByteArrayResource to hold file content in memory
                // Overriding getFilename is crucial for some servers to recognize it as a file
                ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                };
                HttpEntity<ByteArrayResource> fileEntity = new HttpEntity<>(resource, fileHeaders);
                multipartData.add("files", fileEntity); // "files" is the key expected by server
            } catch (Exception e) {
                throw new RuntimeException("Failed to read file", e);
            }
        }

        // Call the new service method with metadata
        return apiService.uploadDocumentWithMetadata(url, multipartData, referenceId, fileMetadataList, null,
                SuccessResponse.class);
    }

    @PostMapping("/download")
    public ResponseEntity<byte[]> downloadPdf(@Valid @RequestBody SampleRequest request) {
        String url = "https://external-api.com/download";

        byte[] pdfBytes = apiService.downloadFileWithJson(url, request, null);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"downloaded.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    // --- DTOs ---

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SampleRequest {
        @NotBlank(message = "data is required")
        private String data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SuccessResponse {
        private String id;
        private String status;
        private String message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorResponse {
        private String errorCode;
        private String errorMessage;
    }
}

package com.api.elifeconnect.service;

import java.util.Map;
import java.util.List;
import org.springframework.util.MultiValueMap;
import org.springframework.http.HttpEntity;
import com.api.elifeconnect.dto.document.DocumentFileMetadata;

public interface GenericApiService {
    
    <T, R> R execute(String url, String method, T requestBody, Map<String, String> headers, Class<R> responseType);

    <T> Object executeWithDynamicResponse(String url, String method, T requestBody, Map<String, String> headers, Map<Integer, Class<?>> statusMapper, Class<?> defaultType);

    <R> R uploadFileWithJson(String url, MultiValueMap<String, HttpEntity<?>> multipartData, Map<String, String> headers, Class<R> responseType);

    <T> byte[] downloadFileWithJson(String url, T requestBody, Map<String, String> headers);

    <R> R uploadDocumentWithMetadata(String url, MultiValueMap<String, HttpEntity<?>> multipartData, 
                                     String referenceId, List<DocumentFileMetadata> fileMetadata,
                                     Map<String, String> headers, Class<R> responseType);

}

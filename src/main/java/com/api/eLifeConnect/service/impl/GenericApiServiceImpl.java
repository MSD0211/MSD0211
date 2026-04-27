package com.api.elifeconnect.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.api.elifeconnect.Utility.WebClientUtil;
import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.aop.LogDocumentOperation;
import com.api.elifeconnect.dto.document.DocumentFileMetadata;
import com.api.elifeconnect.service.GenericApiService;

@Service
public class GenericApiServiceImpl implements GenericApiService {

        private final WebClientUtil client;

        public GenericApiServiceImpl(WebClientUtil client) {
                this.client = client;
        }

        @Override
        public <T, R> R execute(
                        String url,
                        String method,
                        T requestBody,
                        Map<String, String> headers,
                        Class<R> responseType) {

                return client.callApi(method, url, requestBody, headers, responseType)
                                .doFinally(signal -> MDC.remove("apiName"))
                                .block();
        }

        @Override
        public <T> Object executeWithDynamicResponse(
                        String url,
                        String method,
                        T requestBody,
                        Map<String, String> headers,
                        Map<Integer, Class<?>> statusMapper,
                        Class<?> defaultType) {

                return client.callApiWithMapping(method, url, requestBody, headers, statusMapper, defaultType)
                                .doFinally(signal -> MDC.remove("apiName"))
                                .block();
        }

        @Override
        public <R> R uploadFileWithJson(
                        String url,
                        MultiValueMap<String, HttpEntity<?>> multipartData,
                        Map<String, String> headers,
                        Class<R> responseType) {

                return client.uploadPdfWithJson(url, multipartData, headers, responseType)
                                .doFinally(signal -> MDC.remove("apiName"))
                                .block();
        }

        @Override
        public <T> byte[] downloadFileWithJson(
                        String url,
                        T requestBody,
                        Map<String, String> headers) {

                return client.downloadPdf(url, requestBody, headers)
                                .doFinally(signal -> MDC.remove("apiName"))
                                .block();
        }

        @Override
        @LogApiCall("Document Upload API")
        @LogDocumentOperation(operationType = "UPLOAD")
        public <R> R uploadDocumentWithMetadata(
                        String url,
                        MultiValueMap<String, HttpEntity<?>> multipartData,
                        String referenceId,
                        List<DocumentFileMetadata> fileMetadata,
                        Map<String, String> headers,
                        Class<R> responseType) {

                // referenceId & fileMetadata are intentionally kept
                // for AOP logging, audit trail, or future enrichment

                return client.uploadPdfWithJson(url, multipartData, headers, responseType)
                                .doFinally(signal -> MDC.remove("apiName"))
                                .block();
        }
}

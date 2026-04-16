package com.api.elifeconnect.controller.kenya.gp;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.aop.ApiShortNames;
import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.dto.gp.member.ContributionRecordRequestDTO;
import com.api.elifeconnect.service.gp.ContributionRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/kenya/gp/contributionrecord")
public class GpContributionRecordController {

    private final ContributionRecordService contributionRecordService;

    @Autowired
    public GpContributionRecordController(ContributionRecordService contributionRecordService) {
        this.contributionRecordService = contributionRecordService;
    }

    @PostMapping(value = "/statement/download", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "ContributionRecordAPI", shortName = ApiShortNames.CONTRIBUTION_RECORD)
    public ResponseEntity<Resource> downloadContributionRecord(
            @Valid @RequestBody ContributionRecordRequestDTO request) throws IOException {

        MDC.put("apiName", "ContributionRecordAPI");
        byte[] pdfBytes = contributionRecordService.generateContributionRecord(request);

        ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"contribution-record-"
                                + request.schemeNumber() + "-"
                                + request.memberId() + ".pdf\"")
                .contentLength(pdfBytes.length)
                .body(pdfResource);
    }
}
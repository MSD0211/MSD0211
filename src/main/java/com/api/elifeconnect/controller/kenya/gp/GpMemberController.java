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
import com.api.elifeconnect.dto.gp.member.MemberRecordCardRequestDTO;
import com.api.elifeconnect.dto.gp.member.MemberStatementRequestDTO;
import com.api.elifeconnect.service.gp.MemberRecordCardService;
import com.api.elifeconnect.service.gp.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/kenya/gp/member")
public class GpMemberController {

    private final MemberService memberService;
    private final MemberRecordCardService memberRecordCardService;

    @Autowired
    public GpMemberController(MemberService memberService,
                              MemberRecordCardService memberRecordCardService) {
        this.memberService = memberService;
        this.memberRecordCardService = memberRecordCardService;
    }

    @PostMapping(value = "/statement/download", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "MemberStatementAPI", shortName = ApiShortNames.MEMBER_STATEMENT)
    public ResponseEntity<Resource> downloadMemberStatement(
            @Valid @RequestBody MemberStatementRequestDTO request) throws IOException {

        MDC.put("apiName", "MemberStatementAPI");
        byte[] pdfBytes = memberService.generateMemberStatement(request);

        ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"member-statement-"
                                + request.schemeNumber() + "-"
                                + request.memberId() + ".pdf\"")
                .contentLength(pdfBytes.length)
                .body(pdfResource);
    }

    @PostMapping(value = "/record/card/download", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "MemberRecordCardAPI", shortName = ApiShortNames.MEMBER_RECORD_CARD)
    public ResponseEntity<Resource> downloadMemberRecordCard(
            @Valid @RequestBody MemberRecordCardRequestDTO request) throws IOException {

        MDC.put("apiName", "MemberRecordCardAPI");
        byte[] pdfBytes = memberRecordCardService.generateMemberRecordCard(request);

        ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"member-record-card-"
                                + request.schemeNumber() + "-"
                                + request.memberId() + ".pdf\"")
                .contentLength(pdfBytes.length)
                .body(pdfResource);
    }
}
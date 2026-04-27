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
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.gp.member.MemberFundSummaryRequest;
import com.api.elifeconnect.dto.gp.member.MemberFundSummaryResponse;
import com.api.elifeconnect.dto.gp.member.MemberRecordCardRequest;
import com.api.elifeconnect.dto.gp.member.MemberStatementRequest;
import com.api.elifeconnect.service.gp.MemberService;
import com.api.elifeconnect.dto.gp.member.MemberDetailsRequest;
import com.api.elifeconnect.dto.gp.member.MemberDetailsResponse;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/kenya/gp/member")
public class MemberController {

    private final MemberService memberService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping(value = "/statement/download", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "MemberStatementAPI", shortName = ApiShortNames.MEMBER_STATEMENT)
    public ResponseEntity<Resource> downloadMemberStatement(
            @Valid @RequestBody MemberStatementRequest request) throws IOException {

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
            @Valid @RequestBody MemberRecordCardRequest request) throws IOException {

        MDC.put("apiName", "MemberRecordCardAPI");
        byte[] pdfBytes = memberService.generateMemberRecordCard(request);

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

    @PostMapping("/fund/summary")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "Member Fund Summary API", shortName = ApiShortNames.MEMBER_FUND_SUMMARY)
    public ResponseEntity<ApiResponse<MemberFundSummaryResponse>> getMemberFundSummary(
            @Valid @RequestBody MemberFundSummaryRequest memberFundSummaryRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "Member Fund Summary API");
        MemberFundSummaryResponse response = memberService.generateMemberFundSummary(memberFundSummaryRequest);
        ApiResponse<MemberFundSummaryResponse> body = responseBuilder.success(request, "Member Fund Summary generated",
                response);

        return ResponseEntity.ok(body);
    }

    @PostMapping("/details")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "Member Details API", shortName = ApiShortNames.MEMBER_DETAILS)
    public ResponseEntity<ApiResponse<MemberDetailsResponse>> getMemberDetails(
            @Valid @RequestBody MemberDetailsRequest request,
            HttpServletRequest httpRequest) {

        MDC.put("apiName", "Member Details API");
        MemberDetailsResponse data = memberService.getMemberDetails(request); 
        ApiResponse<MemberDetailsResponse> body = responseBuilder.success(httpRequest,
                "Member Details retrieved successfully", data);
        return ResponseEntity.ok(body);
    }

}
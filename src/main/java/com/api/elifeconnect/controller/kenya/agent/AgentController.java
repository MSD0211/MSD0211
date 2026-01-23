package com.api.elifeconnect.controller.kenya.agent;

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

import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.agent.AgentAuthenticationRequest;
import com.api.elifeconnect.dto.agent.AgentAuthenticationResponse;
import com.api.elifeconnect.dto.agent.CommissionStatementRequest;
import com.api.elifeconnect.service.agent.AgentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/kenya/agent")
public class AgentController {

    private final AgentService agentService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/authentication")
    // @PreAuthorize("hasAnyRole('USER')")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("Agent Authentication API")
    public ResponseEntity<ApiResponse<AgentAuthenticationResponse>> agentAuthentication(
            @RequestBody AgentAuthenticationRequest agentAuthenticationRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "Agent Authentication API");
        AgentAuthenticationResponse response = agentService.agentAuthentication(agentAuthenticationRequest);
        System.out.println("RESPONSE ::"+response.message());
        System.out.println("RESPONSE::"+response.emailId());
        ApiResponse<AgentAuthenticationResponse> body =
                responseBuilder.success(request, "Agent Authentication done", response);

        return ResponseEntity.ok(body);
    }

    
    @PostMapping(
            value = "/commission/statement/download",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("CommissionStatementAPI")
    public ResponseEntity<Resource> downloadCommissionStatementPdf(
            @RequestBody CommissionStatementRequest request
    ) throws IOException {

        MDC.put("apiName", "CommissionStatementAPI");
        byte[] pdfBytes = agentService.generateCommissionStatement(request);

        ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"commission-statement-" 
                                + request.agencyCode()+ "_" + request.billMonth() + "_" + request.billYear() + ".pdf\""
                )
                .contentLength(pdfBytes.length)
                .body(pdfResource);
    }

    

}

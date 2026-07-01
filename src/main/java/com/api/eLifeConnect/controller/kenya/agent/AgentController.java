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
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.aop.ApiShortNames;
import com.api.elifeconnect.aop.LogApiCall;
import com.api.elifeconnect.common.response.ApiResponse;
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.agent.AgentAuthenticationRequest;
import com.api.elifeconnect.dto.agent.AgentAuthenticationResponse;
import com.api.elifeconnect.dto.agent.CommissionStatementRequest;
import com.api.elifeconnect.service.agent.AgentService;

import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/kenya/agent")
@org.springframework.validation.annotation.Validated
@Tag(name = "Kenya Agent Controller", description = "Endpoints for Kenya Agent authentication and commission statement download")
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
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "Agent Authentication API", shortName = ApiShortNames.AGENT_AUTH)
        @Operation(summary = "Agent Authentication", description = "Authenticates an agent and returns agent profile details.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Agent successfully authenticated"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<ApiResponse<AgentAuthenticationResponse>> agentAuthentication(
                        @Valid @RequestBody AgentAuthenticationRequest agentAuthenticationRequest,
                        HttpServletRequest request) {

                MDC.put("apiName", "Agent Authentication API");
                AgentAuthenticationResponse response = agentService.agentAuthentication(agentAuthenticationRequest);
                System.out.println("RESPONSE ::" + response.message());
                System.out.println("RESPONSE::" + response.emailId());
                ApiResponse<AgentAuthenticationResponse> body = responseBuilder.success(request,
                                "Agent Authentication done", response);

                return ResponseEntity.ok(body);
        }

        @PostMapping(value = "/commission/statement/download", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
        @PreAuthorize("hasAuthority('kenya.api.read')")
        @LogApiCall(value = "CommissionStatementAPI", shortName = ApiShortNames.AGENT_COMMISSION_STMT)
        @Operation(summary = "Download Commission Statement PDF", description = "Generates and downloads the commission statement PDF for the specified agent and billing period.")
        @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Commission statement PDF file generated successfully"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public ResponseEntity<Resource> downloadCommissionStatementPdf(
                        @Valid @RequestBody CommissionStatementRequest request) throws IOException {

                MDC.put("apiName", "CommissionStatementAPI");
                byte[] pdfBytes = agentService.generateCommissionStatement(request);

                ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

                return ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_PDF)
                                .header(
                                                HttpHeaders.CONTENT_DISPOSITION,
                                                "attachment; filename=\"commission-statement-"
                                                                + request.agencyCode() + "_" + request.billMonth() + "_"
                                                                + request.billYear() + ".pdf\"")
                                .contentLength(pdfBytes.length)
                                .body(pdfResource);
        }

}

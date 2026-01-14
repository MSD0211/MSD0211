package com.api.elifeconnect.controller.kenya.agent;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.api.elifeconnect.service.agent.AgentAuthenticationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/kenya/agent")
public class AgentController {

    private final AgentAuthenticationService agentAuthService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public AgentController(AgentAuthenticationService agentAuthService) {
        this.agentAuthService = agentAuthService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/authentication")
    // @PreAuthorize("hasAnyRole('USER')")
    @PreAuthorize("hasAuthority('api.read')")
    @LogApiCall("Agent Authentication API")
    public ResponseEntity<ApiResponse<AgentAuthenticationResponse>> agentAuthentication(
            @RequestBody AgentAuthenticationRequest agentAuthenticationRequest,
            HttpServletRequest request) {
                
        MDC.put("apiName", "Customer Authentication API");
        AgentAuthenticationResponse response = agentAuthService.agentAuthentication(agentAuthenticationRequest);
        System.out.println("RESPONSE ::"+response.message());
        System.out.println("RESPONSE::"+response.emailId());
        ApiResponse<AgentAuthenticationResponse> body =
                responseBuilder.success(request, "Agent Authentication done", response);

        return ResponseEntity.ok(body);
    }

}

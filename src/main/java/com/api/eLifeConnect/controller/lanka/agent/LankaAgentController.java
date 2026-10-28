package com.api.elifeconnect.controller.lanka.agent;

import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.service.agent.AgentListService;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.api.elifeconnect.dto.agent.DueListRequest;
import com.api.elifeconnect.dto.agent.DueListResponse;
import com.api.elifeconnect.dto.agent.LapseListRequest;
import com.api.elifeconnect.dto.agent.LapseListResponse;

import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/lanka/agent")
@Tag(name = "Agent Services", description = "Endpoints for Lanka agent due list and lapse list operations")
public class LankaAgentController {

    private final AgentListService agentListService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public LankaAgentController(AgentListService agentListService) {
        this.agentListService = agentListService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/duelist")
    @PreAuthorize("hasAuthority('lanka.api.read')")
    @LogApiCall(value = "AgentDueListAPI", shortName = ApiShortNames.AGENT_DUE_LIST)
    @Operation(summary = "Agent Due List", description = "Fetches the list of due policies for an agent.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Due list retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<DueListResponse>> dueList(
            @Valid @RequestBody DueListRequest dueListRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "AgentDueListAPI");
        DueListResponse response = agentListService.fetchDueList(dueListRequest);
        System.out.println("RESPONSE ::" + response.message());
        ApiResponse<DueListResponse> body = responseBuilder.success(request,
                "Agent due list success", response);

        return ResponseEntity.ok(body);
    }

    @PostMapping("/lapselist")
    @PreAuthorize("hasAuthority('lanka.api.read')")
    @LogApiCall(value = "AgentLapseListAPI", shortName = ApiShortNames.AGENT_LAPSE_LIST)
    @Operation(summary = "Agent Lapse List", description = "Fetches the list of lapsed policies for an agent.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lapse list retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ApiResponse<LapseListResponse>> lapseList(
            @Valid @RequestBody LapseListRequest lapseListRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "AgentLapseListAPI");
        LapseListResponse response = agentListService.fetchLapseList(lapseListRequest);
        System.out.println("RESPONSE ::" + response.message());
        ApiResponse<LapseListResponse> body = responseBuilder.success(request,
                "Agent lapse list success", response);

        return ResponseEntity.ok(body);
    }
}

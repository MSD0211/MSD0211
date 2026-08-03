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

@RestController
@RequestMapping("/api/v1/lanka/agent")
public class LankaAgentController {

    private final AgentListService agentListService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public AgentController(AgentListService agentListService) {
        this.agentListService = agentListService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/duelist")
    @PreAuthorize("hasAuthority('lanka.api.read')")
    @LogApiCall(value = "AgentDueListAPI", shortName = ApiShortNames.AGENT_DUE_LIST)
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

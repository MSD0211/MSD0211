package com.api.elifeconnect.controller.kenya.Plan;

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
import com.api.elifeconnect.common.response.ApiResponseBuilder;
import com.api.elifeconnect.dto.plan.Details.PlanDetailsRequest;
import com.api.elifeconnect.dto.plan.Details.PlanDetailsResponse;
import com.api.elifeconnect.service.plan.PlanDetailsService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/kenya/plan")
public class PlanController {

    private final PlanDetailsService planDetailsService;
    private final ApiResponseBuilder responseBuilder;

    @Autowired
    public PlanController(PlanDetailsService planDetailsService) {
        this.planDetailsService = planDetailsService;
        this.responseBuilder = new ApiResponseBuilder();
    }

    @PostMapping("/details")
    // @PreAuthorize("hasAnyRole('USER')")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @LogApiCall(value = "Plan Details API", shortName = ApiShortNames.PLAN_DETAILS)
    public ResponseEntity<ApiResponse<PlanDetailsResponse>> getPlanDetails(
            @Valid @RequestBody PlanDetailsRequest planDetailsRequest,
            HttpServletRequest request) {

        MDC.put("apiName", "Plan Details API");
        PlanDetailsResponse response = planDetailsService.getPlanDetails(planDetailsRequest);
        System.out.println("RESPONSE ::" + response.message());
        System.out.println("RESPONSE::" + response.plans());
        ApiResponse<PlanDetailsResponse> body = responseBuilder.success(request, "Plan Details Successful", response);

        return ResponseEntity.ok(body);
    }
}

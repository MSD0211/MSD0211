package com.api.elifeconnect.service.plan;

import com.api.elifeconnect.dto.plan.Details.PlanDetailsRequest;
import com.api.elifeconnect.dto.plan.Details.PlanDetailsResponse;

public interface PlanDetailsService {

    PlanDetailsResponse getPlanDetails(PlanDetailsRequest req);

}

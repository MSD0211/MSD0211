package com.api.elifeconnect.service.agent;

import com.api.elifeconnect.dto.agent.DueListRequest;
import com.api.elifeconnect.dto.agent.DueListResponse;
import com.api.elifeconnect.dto.agent.LapseListRequest;
import com.api.elifeconnect.dto.agent.LapseListResponse;

public interface AgentListService {

    DueListResponse fetchDueList(DueListRequest req);

    LapseListResponse fetchLapseList(LapseListRequest req);
}

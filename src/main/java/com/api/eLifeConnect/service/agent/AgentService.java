package com.api.elifeconnect.service.agent;

import com.api.elifeconnect.dto.agent.AgentAuthenticationRequest;
import com.api.elifeconnect.dto.agent.AgentAuthenticationResponse;
import com.api.elifeconnect.dto.agent.CommissionStatementRequest;

public interface AgentService {

    AgentAuthenticationResponse agentAuthentication(AgentAuthenticationRequest req);
    byte[] generateCommissionStatement(CommissionStatementRequest commissionStatementRequest);

}

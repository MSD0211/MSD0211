package com.api.elifeconnect.service.agent;

import com.api.elifeconnect.dto.agent.AgentAuthenticationRequest;
import com.api.elifeconnect.dto.agent.AgentAuthenticationResponse;

public interface AgentAuthenticationService {

    AgentAuthenticationResponse agentAuthentication(AgentAuthenticationRequest req);

}

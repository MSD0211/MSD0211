package com.api.elifeconnect.dto.proposal.submit;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ProposalSubmitRequest(
        
        @JsonAlias({"referenceId"})
        String id,
        List<BocDetails> boc_details,
        AgentDetails agent_details,
        PfPlanDetails pf_plan_details,
        ProposerDetails proposer_details,
        HealthStatements health_statements,
        ProposerDetails proposer_details_2,
        List<UploadedDocument> uploaded_documents,
        HealthStatements health_statements_2
) {}

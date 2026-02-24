package com.api.elifeconnect.dto.proposal.submit;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProposalSubmitRequest(
        @JsonAlias({"referenceId"})
        @NotBlank(message = "referenceId is required")
        @Size(max = 30)
        String id,

        @NotNull(message = "boc_details is required")
        @NotEmpty(message = "boc_details cannot be empty")
        List<@Valid BocDetails> boc_details,

        @NotNull(message = "agent_details is required")
        @Valid
        AgentDetails agent_details,

        @NotNull(message = "pf_plan_details is required")
        @Valid
        PfPlanDetails pf_plan_details,

        @NotNull(message = "proposer_details is required")
        @Valid
        ProposerDetails proposer_details,

        @Valid
        HealthStatements health_statements,

        @Valid
        ProposerDetails proposer_details_2,

        List<@Valid UploadedDocument> uploaded_documents,

        @Valid
        HealthStatements health_statements_2
) {}

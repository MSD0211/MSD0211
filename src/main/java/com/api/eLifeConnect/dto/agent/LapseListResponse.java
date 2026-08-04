package com.api.elifeconnect.dto.agent;

import java.util.List;

public record LapseListResponse(
                List<LapseListItem> policies,
                String message
) {

        public record LapseListItem(
                        String policyNumber,
                        String doc,
                        String plan,
                        String policyTerm,
                        String sumAssd,
                        String policyMode,
                        String instPrem,
                        String fup,
                        String policyStatus
        ) {
        }
}

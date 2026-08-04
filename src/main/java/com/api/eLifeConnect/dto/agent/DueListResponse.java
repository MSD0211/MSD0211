package com.api.elifeconnect.dto.agent;

import java.util.List;

public record DueListResponse(
                List<DueListItem> policies,
                String message
) {

        public record DueListItem(
                        String policyNumber,
                        String doc,
                        String plan,
                        String term,
                        String sumAssd,
                        String mode,
                        String instPrem,
                        String fup,
                        String totPaidYrs,
                        String mobileNumber
        ) {
        }
}

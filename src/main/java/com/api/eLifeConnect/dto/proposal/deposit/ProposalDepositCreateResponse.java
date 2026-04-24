package com.api.elifeconnect.dto.proposal.deposit;

import java.time.LocalDate;

public record ProposalDepositCreateResponse(

        String code,
        String message,
        String bocNumber,
        LocalDate bocDate

) {
}
package com.api.elifeconnect.dto.proposal.deposit;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ProposalDepositCreationResponse(

        @JsonAlias({ "code" })
        String code,

        @JsonAlias({ "message" })
        String message,

        @JsonAlias({ "bocNumber", "boc_no" })
        String bocNumber,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @JsonAlias({ "bocDate", "boc_date" })
        LocalDate bocDate
) {}


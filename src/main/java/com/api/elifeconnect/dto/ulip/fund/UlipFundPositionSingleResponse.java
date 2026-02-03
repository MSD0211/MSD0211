package com.api.elifeconnect.dto.ulip.fund;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;


public record UlipFundPositionSingleResponse(


        String policyNumber,
        String name,
        String plan,
        BigDecimal sumAssured,
        LocalDate doc,
        String type,
        LocalDate fundAsOn,
        BigDecimal investedAmount,
        BigDecimal interestEarned,
        BigDecimal withDrawals,
        BigDecimal totalDeductions,
        BigDecimal fundValue
) {}

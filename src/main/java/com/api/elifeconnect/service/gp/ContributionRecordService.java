package com.api.elifeconnect.service.gp;

import com.api.elifeconnect.dto.gp.member.ContributionRecordRequestDTO;

public interface ContributionRecordService {
    byte[] generateContributionRecord(ContributionRecordRequestDTO request);
}
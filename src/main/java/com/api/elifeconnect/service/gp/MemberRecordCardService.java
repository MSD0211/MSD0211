package com.api.elifeconnect.service.gp;

import com.api.elifeconnect.dto.gp.member.MemberRecordCardRequestDTO;

public interface MemberRecordCardService {
    byte[] generateMemberRecordCard(MemberRecordCardRequestDTO request);
}
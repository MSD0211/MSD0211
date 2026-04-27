package com.api.elifeconnect.service.gp;

import com.api.elifeconnect.dto.gp.member.MemberFundSummaryRequest;
import com.api.elifeconnect.dto.gp.member.MemberFundSummaryResponse;
import com.api.elifeconnect.dto.gp.member.MemberRecordCardRequest;
import com.api.elifeconnect.dto.gp.member.MemberStatementRequest;

public interface MemberService {

  
    byte[] generateMemberStatement(MemberStatementRequest request);
    byte[] generateMemberRecordCard(MemberRecordCardRequest request);
    MemberFundSummaryResponse generateMemberFundSummary(MemberFundSummaryRequest request);
    MemberDetailsResponse getMemberDetails(MemberDetailsRequest request);

}


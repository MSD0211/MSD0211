package com.api.elifeconnect.service.gp;

import com.api.elifeconnect.dto.gp.member.MemberStatementRequestDTO;

public interface MemberService {

  
    byte[] generateMemberStatement(MemberStatementRequestDTO request);

}


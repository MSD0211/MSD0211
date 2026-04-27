package com.api.elifeconnect.dto.gp.scheme;

import java.util.List;

public record SchemeMemberDetailsResponse(

        List<MemberDetails> memberDetails

) {

    public record MemberDetails(
            String memberId,
            String name,
            String status
    ) {}

}

package com.api.elifeconnect.dto.gp.scheme;

import java.util.List;

public record NationalIdSchemesResponse(

        List<NationalIdScheme> schemes

) {

    public record NationalIdScheme(
            String schemeId,
            String schemeName,
            String memberId,
            Integer productId
    ) {
    }
}
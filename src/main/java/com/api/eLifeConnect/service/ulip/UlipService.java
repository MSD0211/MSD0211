package com.api.elifeconnect.service.ulip;

import com.api.elifeconnect.dto.ulip.fund.UlipFundPositionSingleResponse;
import com.api.elifeconnect.dto.ulip.fund.UlipFundPositionSingleRequest;

public interface UlipService {

     UlipFundPositionSingleResponse getFundPositionSingle(UlipFundPositionSingleRequest req);
}

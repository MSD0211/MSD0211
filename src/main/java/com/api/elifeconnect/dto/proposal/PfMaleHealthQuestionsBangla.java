package com.api.elifeconnect.dto.proposal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PfMaleHealthQuestionsBangla {

    @JsonProperty("HS_PMHQ_01")
    String HSPMHQ01;

    @JsonProperty("HS_PMHQ_02")
    String HSPMHQ02;

    public PfMaleHealthQuestionsBangla() {
    }

    public PfMaleHealthQuestionsBangla(String HSPMHQ01, String HSPMHQ02) {
        this.HSPMHQ01 = HSPMHQ01;
        this.HSPMHQ02 = HSPMHQ02;
    }

    public void setHSPMHQ01(String HSPMHQ01) {
        this.HSPMHQ01 = HSPMHQ01;
    }

    public String getHSPMHQ01() {
        return HSPMHQ01;
    }

    public void setHSPMHQ02(String HSPMHQ02) {
        this.HSPMHQ02 = HSPMHQ02;
    }

    public String getHSPMHQ02() {
        return HSPMHQ02;
    }

}

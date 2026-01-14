package com.api.elifeconnect.dto.proposal.submit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PfFemaleHealthQuestionsBangla {

    @JsonProperty("HS_PF_FHQ_01")
    String HSPFFHQ01;

    @JsonProperty("HS_PF_FHQ_02")
    String HSPFFHQ02;

    @JsonProperty("HS_PF_FHQ_03")
    String HSPFFHQ03;

    @JsonProperty("HS_PF_FHQ_04")
    String HSPFFHQ04;

    @JsonProperty("HS_PF_FHQ_05")
    String HSPFFHQ05;

    @JsonProperty("HS_PF_FHQ_06")
    String HSPFFHQ06;

    @JsonProperty("HS_PF_FHQ_07")
    String HSPFFHQ07;

    @JsonProperty("HS_PF_FHQ_08")
    String HSPFFHQ08;

    public PfFemaleHealthQuestionsBangla() {
    }

    public PfFemaleHealthQuestionsBangla(String HSPFFHQ01, String HSPFFHQ02, String HSPFFHQ03, String HSPFFHQ04,
            String HSPFFHQ05, String HSPFFHQ06, String HSPFFHQ07, String HSPFFHQ08) {
        this.HSPFFHQ01 = HSPFFHQ01;
        this.HSPFFHQ02 = HSPFFHQ02;
        this.HSPFFHQ03 = HSPFFHQ03;
        this.HSPFFHQ04 = HSPFFHQ04;
        this.HSPFFHQ05 = HSPFFHQ05;
        this.HSPFFHQ06 = HSPFFHQ06;
        this.HSPFFHQ07 = HSPFFHQ07;
        this.HSPFFHQ08 = HSPFFHQ08;
    }

    public void setHSPFFHQ01(String HSPFFHQ01) {
        this.HSPFFHQ01 = HSPFFHQ01;
    }

    public String getHSPFFHQ01() {
        return HSPFFHQ01;
    }

    public void setHSPFFHQ02(String HSPFFHQ02) {
        this.HSPFFHQ02 = HSPFFHQ02;
    }

    public String getHSPFFHQ02() {
        return HSPFFHQ02;
    }

    public void setHSPFFHQ03(String HSPFFHQ03) {
        this.HSPFFHQ03 = HSPFFHQ03;
    }

    public String getHSPFFHQ03() {
        return HSPFFHQ03;
    }

    public void setHSPFFHQ04(String HSPFFHQ04) {
        this.HSPFFHQ04 = HSPFFHQ04;
    }

    public String getHSPFFHQ04() {
        return HSPFFHQ04;
    }

    public void setHSPFFHQ05(String HSPFFHQ05) {
        this.HSPFFHQ05 = HSPFFHQ05;
    }

    public String getHSPFFHQ05() {
        return HSPFFHQ05;
    }

    public void setHSPFFHQ06(String HSPFFHQ06) {
        this.HSPFFHQ06 = HSPFFHQ06;
    }

    public String getHSPFFHQ06() {
        return HSPFFHQ06;
    }

    public void setHSPFFHQ07(String HSPFFHQ07) {
        this.HSPFFHQ07 = HSPFFHQ07;
    }

    public String getHSPFFHQ07() {
        return HSPFFHQ07;
    }

    public void setHSPFFHQ08(String HSPFFHQ08) {
        this.HSPFFHQ08 = HSPFFHQ08;
    }

    public String getHSPFFHQ08() {
        return HSPFFHQ08;
    }

}

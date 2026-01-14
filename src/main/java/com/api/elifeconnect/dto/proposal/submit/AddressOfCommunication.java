package com.api.elifeconnect.dto.proposal.submit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressOfCommunication {
    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private String tel;
    private String mob;

    public AddressOfCommunication()
    {
     
    }
    public AddressOfCommunication(String line1, String line2, String line3, String line4, String tel, String mob) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        this.tel = tel;
        this.mob = mob;
    }

    public String getLine1() {
        return this.line1;
    }

    public String getLine2() {
        return this.line2;
    }

    public String getLine3() {
        return this.line3;
    }

    public String getLine4() {
        return this.line4;
    }

    public String getTel() {
        return this.tel;
    }

    public String getMob() {
        return this.mob;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }
}


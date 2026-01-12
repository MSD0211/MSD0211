package com.api.elifeconnect.dto.proposal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgentDetailsBangla {
    private String name;
    private String code;
    private String email;
    private String type;
    @JsonProperty("mobile_no")
    private String mobileNo;

    public AgentDetailsBangla() {
    }

    public AgentDetailsBangla(String name, String code, String email, String type, String mobileNo) {
        this.name = name;
        this.code = code;
        this.email = email;
        this.type = type;
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getEmail() {
        return this.email;
    }

    public String getType() {
        return this.type;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }
}



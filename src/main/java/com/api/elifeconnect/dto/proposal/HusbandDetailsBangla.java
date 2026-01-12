package com.api.elifeconnect.dto.proposal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HusbandDetailsBangla {

    @JsonProperty("name")
    String name;

    @JsonProperty("income")
    String income;

    @JsonProperty("occupation")
    String occupation;

    public HusbandDetailsBangla() {
    }

    public HusbandDetailsBangla(String name, String income, String occupation) {
        this.name = name;
        this.income = income;
        this.occupation = occupation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getIncome() {
        return income;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }

}
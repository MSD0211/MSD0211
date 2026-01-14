package com.api.elifeconnect.dto.proposal.submit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FamilyHistory {
    private String relationship;
    private String age;
    @JsonProperty("vital_status")
    private String vitalStatus;
    private String description;

    public FamilyHistory()
    {
     
    }
    public FamilyHistory(String relationship, String age, String vitalStatus, String description) {
        this.relationship = relationship;
        this.age = age;
        this.vitalStatus = vitalStatus;
        this.description = description;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public String getAge() {
        return this.age;
    }

    public String getVitalStatus() {
        return this.vitalStatus;
    }

    public String getDescription() {
        return this.description;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setVitalStatus(String vitalStatus) {
        this.vitalStatus = vitalStatus;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


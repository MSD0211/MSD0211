package com.api.elifeconnect.dto.proposal.submit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HusbandInsurancesBangla {

    @JsonProperty("sum_assured")
    String sumAssured;

    @JsonProperty("plan_and_term")
    String planAndTerm;

    @JsonProperty("policy_number")
    String policyNumber;

    @JsonProperty("present_status")
    String presentStatus;

    @JsonProperty("insurance_company_name_and_address")
    String insuranceCompanyNameAndAddress;

    public HusbandInsurancesBangla() {
    }

    public HusbandInsurancesBangla(String sumAssured, String planAndTerm, String policyNumber, String presentStatus,
            String insuranceCompanyNameAndAddress) {
        this.sumAssured = sumAssured;
        this.planAndTerm = planAndTerm;
        this.policyNumber = policyNumber;
        this.presentStatus = presentStatus;
        this.insuranceCompanyNameAndAddress = insuranceCompanyNameAndAddress;
    }

    public void setSumAssured(String sumAssured) {
        this.sumAssured = sumAssured;
    }

    public String getSumAssured() {
        return sumAssured;
    }

    public void setPlanAndTerm(String planAndTerm) {
        this.planAndTerm = planAndTerm;
    }

    public String getPlanAndTerm() {
        return planAndTerm;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPresentStatus(String presentStatus) {
        this.presentStatus = presentStatus;
    }

    public String getPresentStatus() {
        return presentStatus;
    }

    public void setInsuranceCompanyNameAndAddress(String insuranceCompanyNameAndAddress) {
        this.insuranceCompanyNameAndAddress = insuranceCompanyNameAndAddress;
    }

    public String getInsuranceCompanyNameAndAddress() {
        return insuranceCompanyNameAndAddress;
    }

}
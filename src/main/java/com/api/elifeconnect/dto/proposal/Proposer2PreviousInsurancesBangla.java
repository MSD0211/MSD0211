package com.api.elifeconnect.dto.proposal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Proposer2PreviousInsurancesBangla {

    @JsonProperty("policy_term")
    String policyTerm;

    @JsonProperty("sum_assured")
    String sumAssured;

    @JsonProperty("policy_number")
    String policyNumber;

    @JsonProperty("year_of_issue")
    String yearOfIssue;

    @JsonProperty("accident_benefit")
    String accidentBenefit;

    @JsonProperty("reason_for_cover")
    String reasonForCover;

    @JsonProperty("present_status_of_policy")
    String presentStatusOfPolicy;

    @JsonProperty("name_of_insurance_company")
    String nameOfInsuranceCompany;

    public Proposer2PreviousInsurancesBangla()
    {

    }

    public Proposer2PreviousInsurancesBangla(String policyTerm, String sumAssured, String policyNumber, String yearOfIssue, String accidentBenefit,String reasonForCover, String presentStatusOfPolicy, String nameOfInsuranceCompany) {
        this.policyTerm = policyTerm;
        this.sumAssured = sumAssured;
        this.policyNumber = policyNumber;
        this.yearOfIssue = yearOfIssue;
        this.accidentBenefit = accidentBenefit;
        this.reasonForCover = reasonForCover;
        this.presentStatusOfPolicy = presentStatusOfPolicy;
        this.nameOfInsuranceCompany = nameOfInsuranceCompany;
    }

    public void setPolicyTerm(String policyTerm) {
        this.policyTerm = policyTerm;
    }
    public String getPolicyTerm() {
        return policyTerm;
    }

    public void setSumAssured(String sumAssured) {
        this.sumAssured = sumAssured;
    }
    public String getSumAssured() {
        return sumAssured;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setYearOfIssue(String yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }
    public String getYearOfIssue() {
        return yearOfIssue;
    }

    public void setAccidentBenefit(String accidentBenefit) {
        this.accidentBenefit = accidentBenefit;
    }
    public String getAccidentBenefit() {
        return accidentBenefit;
    }

    public void setReasonForCover(String reasonForCover) {
        this.reasonForCover = reasonForCover;
    }
    public String getReasonForCover() {
        return reasonForCover;
    }

    public void setPresentStatusOfPolicy(String presentStatusOfPolicy) {
        this.presentStatusOfPolicy = presentStatusOfPolicy;
    }
    public String getPresentStatusOfPolicy() {
        return presentStatusOfPolicy;
    }

    public void setNameOfInsuranceCompany(String nameOfInsuranceCompany) {
        this.nameOfInsuranceCompany = nameOfInsuranceCompany;
    }
    public String getNameOfInsuranceCompany() {
        return nameOfInsuranceCompany;
    }

}

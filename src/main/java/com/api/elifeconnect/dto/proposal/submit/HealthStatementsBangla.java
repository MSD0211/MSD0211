package com.api.elifeconnect.dto.proposal.submit;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HealthStatementsBangla {

    @JsonProperty("mer")
    Mer mer;

    @JsonProperty("height")
    String height;

    @JsonProperty("weight")
    String weight;

    @JsonProperty("family_history")
    List<FamilyHistory> familyHistory;

    @JsonProperty("husband_details")
    HusbandDetailsBangla husbandDetails;

    @JsonProperty("husband_insurances")
    List<HusbandInsurancesBangla> husbandInsurances;

    @JsonProperty("pf_health_questions")
    PfHealthQuestionsBangla pfHealthQuestions;

    @JsonProperty("smoker_status")
    String smokerStatus;

    @JsonProperty("date_of_last_delivery")
    String dateOfLastDelivery;

    @JsonProperty("date_of_last_mensuration")
    String dateOfLastMensuration;

    @JsonProperty("pf_male_health_questions")
    PfMaleHealthQuestionsBangla pfMaleHealthQuestions;

    @JsonProperty("pf_female_health_questions")
    PfFemaleHealthQuestionsBangla pfFemaleHealthQuestions;

    @JsonProperty("is_willing_to_answer_health_questions")
    String isWillingToAnswerHealthQuestions;

    public HealthStatementsBangla() {
    }

    public HealthStatementsBangla(Mer mer, String height, String weight, List<FamilyHistory> familyHistory,
            HusbandDetailsBangla husbandDetails, List<HusbandInsurancesBangla> husbandInsurances,
            PfHealthQuestionsBangla pfHealthQuestions, String smokerStatus, String dateOfLastDelivery,
            String dateOfLastMensuration, PfMaleHealthQuestionsBangla pfMaleHealthQuestions,
            PfFemaleHealthQuestionsBangla pfFemaleHealthQuestions, String isWillingToAnswerHealthQuestions) {
        this.mer = mer;
        this.height = height;
        this.weight = weight;
        this.familyHistory = familyHistory;
        this.husbandDetails = husbandDetails;
        this.husbandInsurances = husbandInsurances;
        this.pfHealthQuestions = pfHealthQuestions;
        this.smokerStatus = smokerStatus;
        this.dateOfLastDelivery = dateOfLastDelivery;
        this.dateOfLastMensuration = dateOfLastMensuration;
        this.pfMaleHealthQuestions = pfMaleHealthQuestions;
        this.pfFemaleHealthQuestions = pfFemaleHealthQuestions;
        this.isWillingToAnswerHealthQuestions = isWillingToAnswerHealthQuestions;
    }

    public void setMer(Mer mer) {
        this.mer = mer;
    }

    public Mer getMer() {
        return mer;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setFamilyHistory(List<FamilyHistory> familyHistory) {
        this.familyHistory = familyHistory;
    }

    public List<FamilyHistory> getFamilyHistory() {
        return familyHistory;
    }

    public void setHusbandDetails(HusbandDetailsBangla husbandDetails) {
        this.husbandDetails = husbandDetails;
    }

    public HusbandDetailsBangla getHusbandDetails() {
        return husbandDetails;
    }

    public void setHusbandInsurances(List<HusbandInsurancesBangla> husbandInsurances) {
        this.husbandInsurances = husbandInsurances;
    }

    public List<HusbandInsurancesBangla> getHusbandInsurances() {
        return husbandInsurances;
    }

    public void setPfHealthQuestions(PfHealthQuestionsBangla pfHealthQuestions) {
        this.pfHealthQuestions = pfHealthQuestions;
    }

    public PfHealthQuestionsBangla getPfHealthQuestions() {
        return pfHealthQuestions;
    }

    public void setSmokerStatus(String smokerStatus) {
        this.smokerStatus = smokerStatus;
    }

    public String getSmokerStatus() {
        return smokerStatus;
    }

    public void setDateOfLastDelivery(String dateOfLastDelivery) {
        this.dateOfLastDelivery = dateOfLastDelivery;
    }

    public String getDateOfLastDelivery() {
        return dateOfLastDelivery;
    }

    public void setDateOfLastMensuration(String dateOfLastMensuration) {
        this.dateOfLastMensuration = dateOfLastMensuration;
    }

    public String getDateOfLastMensuration() {
        return dateOfLastMensuration;
    }

    public void setPfMaleHealthQuestions(PfMaleHealthQuestionsBangla pfMaleHealthQuestions) {
        this.pfMaleHealthQuestions = pfMaleHealthQuestions;
    }

    public PfMaleHealthQuestionsBangla getPfMaleHealthQuestions() {
        return pfMaleHealthQuestions;
    }

    public void setPfFemaleHealthQuestions(PfFemaleHealthQuestionsBangla pfFemaleHealthQuestions) {
        this.pfFemaleHealthQuestions = pfFemaleHealthQuestions;
    }

    public PfFemaleHealthQuestionsBangla getPfFemaleHealthQuestions() {
        return pfFemaleHealthQuestions;
    }

    public void setIsWillingToAnswerHealthQuestions(String isWillingToAnswerHealthQuestions) {
        this.isWillingToAnswerHealthQuestions = isWillingToAnswerHealthQuestions;
    }

    public String getIsWillingToAnswerHealthQuestions() {
        return isWillingToAnswerHealthQuestions;
    }

}

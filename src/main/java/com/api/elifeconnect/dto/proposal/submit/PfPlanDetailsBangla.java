package com.api.elifeconnect.dto.proposal.submit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PfPlanDetailsBangla {
    private String mode;
    private String plan;
    private String term;
    private String premium;
    @JsonProperty("sum_assured")
    private String sumAssured;
    @JsonProperty("insurance_rider")
    private String insuranceRider;
    @JsonProperty("nominee_details")
    private NomineeDetailsBangla nomineeDetails;
    @JsonProperty("appointee_details")
    private AppointeeDetailsBangla appointeeDetails;
    @JsonProperty("premium_paying_term")
    private String premiumPayingTerm;
    @JsonProperty("date_of_commencement")
    private String dateOfCommencement;
    @JsonProperty("critical_illness_rider")
    private String criticalIllnessRider;
    @JsonProperty("ci_rider_sa")
    private String criticalIllnessRiderSa;
    @JsonProperty("health_rider_option")
    private String healthRiderOption;
    @JsonProperty("hcb_rider_sa")
    private String hcbRiderSa;
    @JsonProperty("scb_rider_sa")
    private String scbRiderSa;
    @JsonProperty("term_rider_sa")
    private String termRiderSa;
    @JsonProperty("sum_assured_for_critical_illness_rider")
    private String sumAssuredRequiredForCriticalIllnessRider;
    @JsonProperty("proposer_1_purpose_of_insurance")
    private String proposer1PurposeOfInsurance;
    @JsonProperty("proposer_1_have_previous_insurance")
    private String proposer1HavePreviousInsurance;
    @JsonProperty("proposer_1_previous_insurances")
    private List<Proposer1PreviousInsurancesBangla> proposer1PreviousInsurances;
    @JsonProperty("proposer_2_purpose_of_insurance")
    private String proposer2PurposeOfInsurance;
    @JsonProperty("proposer_2_have_previous_insurance")
    private String proposer2HavePreviousInsurance;
    @JsonProperty("proposer_2_previous_insurances")
    private List<Proposer2PreviousInsurancesBangla> proposer2PreviousInsurances;
    @JsonProperty("bank_details_for_payment_of_premium")
    private BankDetailsForPaymentOfPremiumBangla bankDetailsForPaymentOfPremium;
    @JsonProperty("sum_assured_required_for_double_accident_benefits")
    private String sumAssuredRequiredForDoubleAccidentBenefits; 

    public PfPlanDetailsBangla()
    {
     
    }
    public PfPlanDetailsBangla(String mode,String plan, String term, String premium, String sumAssured, String insuranceRider, NomineeDetailsBangla nomineeDetails, AppointeeDetailsBangla appointeeDetails, String premiumPayingTerm,  String dateOfCommencement, String criticalIllnessRider,String criticalIllnessRiderSa,String healthRiderOption, String hcbRiderSa,String scbRiderSa,String termRiderSa,String sumAssuredRequiredForCriticalIllnessRider,String proposer1PurposeOfInsurance, String proposer1HavePreviousInsurance, List<Proposer1PreviousInsurancesBangla> proposer1PreviousInsurances,String proposer2PurposeOfInsurance,String proposer2HavePreviousInsurance, List<Proposer2PreviousInsurancesBangla> proposer2PreviousInsurances, BankDetailsForPaymentOfPremiumBangla bankDetailsForPaymentOfPremium, String sumAssuredRequiredForDoubleAccidentBenefits) {
        this.mode = mode;
        this.plan = plan;
        this.term = term;
        this.premium = premium;
        this.sumAssured = sumAssured;
        this.insuranceRider = insuranceRider;
        this.nomineeDetails = nomineeDetails;
        this.appointeeDetails = appointeeDetails;
        this.premiumPayingTerm = premiumPayingTerm;
        this.dateOfCommencement = dateOfCommencement;
        this.criticalIllnessRider = criticalIllnessRider;
        this.criticalIllnessRiderSa = criticalIllnessRiderSa;
        this.healthRiderOption = healthRiderOption;
        this.hcbRiderSa = hcbRiderSa;
        this.scbRiderSa = scbRiderSa;
        this.termRiderSa = termRiderSa;
        this.sumAssuredRequiredForCriticalIllnessRider = sumAssuredRequiredForCriticalIllnessRider;
        this.proposer1PurposeOfInsurance = proposer1PurposeOfInsurance;
        this.proposer1HavePreviousInsurance = proposer1HavePreviousInsurance;
        this.proposer1PreviousInsurances = proposer1PreviousInsurances;
        this.proposer2PurposeOfInsurance = proposer2PurposeOfInsurance;
        this.proposer2HavePreviousInsurance = proposer2HavePreviousInsurance;
        this.proposer2PreviousInsurances = proposer2PreviousInsurances;
        this.bankDetailsForPaymentOfPremium = bankDetailsForPaymentOfPremium;
        this.sumAssuredRequiredForDoubleAccidentBenefits = sumAssuredRequiredForDoubleAccidentBenefits;
    }

    public String getPlan() {
        return this.plan;
    }

    public String getTerm() {
        return this.term;
    }

    public String getPremiumPayingTerm() {
        return this.premiumPayingTerm;
    }

    public String getSumAssured() {
        return this.sumAssured;
    }

    public String getMode() {
        return this.mode;
    }

    public String getPremium() {
        return this.premium;
    }

    public String getDateOfCommencement() {
        return this.dateOfCommencement;
    }

    public String getCriticalIllnessRider() {
        return this.criticalIllnessRider;
    }

    public String getCriticalIllnessRiderSa() {
        return this.criticalIllnessRiderSa;
    }

    public String getHealthRiderOption() {
        return this.healthRiderOption;
    }

    public String getHcbRiderSa() {
        return this.hcbRiderSa;
    }

    public String getScbRiderSa() {
        return this.scbRiderSa;
    }

    public String getTermRiderSa() {
        return this.termRiderSa;
    }

    public String getSumAssuredRequiredForCriticalIllnessRider() {
        return this.sumAssuredRequiredForCriticalIllnessRider;
    }

    public String getSumAssuredRequiredForDoubleAccidentBenefits() {
        return this.sumAssuredRequiredForDoubleAccidentBenefits;
    }

    public String getInsuranceRider() {
        return this.insuranceRider;
    }

    public String getProposer1HavePreviousInsurance() {
        return this.proposer1HavePreviousInsurance;
    }

    public List<Proposer1PreviousInsurancesBangla> getProposer1PreviousInsurances() {
        return this.proposer1PreviousInsurances;
    }

    public String getProposer1PurposeOfInsurance() {
        return this.proposer1PurposeOfInsurance;
    }

    public String getProposer2HavePreviousInsurance() {
        return this.proposer2HavePreviousInsurance;
    }

    public List<Proposer2PreviousInsurancesBangla> getProposer2PreviousInsurances() {
        return this.proposer2PreviousInsurances;
    }

    public String getProposer2PurposeOfInsurance() {
        return this.proposer2PurposeOfInsurance;
    }

    public BankDetailsForPaymentOfPremiumBangla getBankDetailsForPaymentOfPremium() {
        return this.bankDetailsForPaymentOfPremium;
    }

    public NomineeDetailsBangla getNomineeDetails() {
        return this.nomineeDetails;
    }

    public AppointeeDetailsBangla getAppointeeDetails() {
        return this.appointeeDetails;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setPremiumPayingTerm(String premiumPayingTerm) {
        this.premiumPayingTerm = premiumPayingTerm;
    }

    public void setSumAssured(String sumAssured) {
        this.sumAssured = sumAssured;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public void setSumAssuredRequiredForDoubleAccidentBenefits(String sumAssuredRequiredForDoubleAccidentBenefits) {
        this.sumAssuredRequiredForDoubleAccidentBenefits = sumAssuredRequiredForDoubleAccidentBenefits;
    }

    public void setCriticalIllnessRider(String criticalIllnessRider) {
        this.criticalIllnessRider = criticalIllnessRider;
    }

    public void setCriticalIllnessRiderSa(String criticalIllnessRiderSa) {
        this.criticalIllnessRiderSa = criticalIllnessRiderSa;
    }

    public void setHealthRiderOption(String healthRiderOption) {
        this.healthRiderOption = healthRiderOption;
    }

    public void setHcbRiderSa(String hcbRiderSa) {
        this.hcbRiderSa = hcbRiderSa;
    }

    public void setScbRiderSa(String scbRiderSa) {
        this.scbRiderSa = scbRiderSa;
    }

    public void setTermRiderSa(String termRiderSa) {
        this.termRiderSa = termRiderSa;
    }

    public void setSumAssuredRequiredForCriticalIllnessRider(String sumAssuredRequiredForCriticalIllnessRider) {
        this.sumAssuredRequiredForCriticalIllnessRider = sumAssuredRequiredForCriticalIllnessRider;
    }

    public void setInsuranceRider(String insuranceRider) {
        this.insuranceRider = insuranceRider;
    }

    public void setProposer1HavePreviousInsurance(String proposer1HavePreviousInsurance) {
        this.proposer1HavePreviousInsurance = proposer1HavePreviousInsurance;
    }

    public void setProposer1PreviousInsurances(List<Proposer1PreviousInsurancesBangla> proposer1PreviousInsurances) {
        this.proposer1PreviousInsurances = proposer1PreviousInsurances;
    }

    public void setProposer1PurposeOfInsurance(String proposer1PurposeOfInsurance) {
        this.proposer1PurposeOfInsurance = proposer1PurposeOfInsurance;
    }

    public void setProposer2HavePreviousInsurance(String proposerr2avePreviousInsurance) {
        this.proposer2HavePreviousInsurance = proposer1HavePreviousInsurance;
    }

    public void setProposer2PreviousInsurances(List<Proposer2PreviousInsurancesBangla> proposer2PreviousInsurances) {
        this.proposer2PreviousInsurances = proposer2PreviousInsurances;
    }

    public void setProposer2PurposeOfInsurance(String proposer2PurposeOfInsurance) {
        this.proposer2PurposeOfInsurance = proposer2PurposeOfInsurance;
    }

    public void setBankDetailsForPaymentOfPremium(BankDetailsForPaymentOfPremiumBangla bankDetailsForPaymentOfPremium) {
        this.bankDetailsForPaymentOfPremium = bankDetailsForPaymentOfPremium;
    }

    public void setNomineeDetails(NomineeDetailsBangla nomineeDetails) {
        this.nomineeDetails = nomineeDetails;
    }

    public void setAppointeeDetails(AppointeeDetailsBangla appointeeDetails) {
        this.appointeeDetails = appointeeDetails;
    }
}


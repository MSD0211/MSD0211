package com.api.elifeconnect.dto.proposal.submit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProposalSubmitRequest {

    @JsonProperty("age")
    int age;

    @JsonProperty("dob")
    String dob;

    @JsonProperty("gender")
    String gender;

    @JsonProperty("email_id")
    String emailId;

    @JsonProperty("education")
    String education;

    @JsonProperty("occupation")
    String occupation;

    @JsonProperty("customer_id")
    String customerId;

    @JsonProperty("father_name")
    String fatherName;

    @JsonProperty("employer_name")
    String employerName;

    @JsonProperty("marital_status")
    String maritalStatus;

    @JsonProperty("place_of_birth")
    String placeOfBirth;

    @JsonProperty("length_of_service")
    String lengthOfService;

    @JsonProperty("proposer_last_name")
    String proposerLastName;

    @JsonProperty("proposer_first_name")
    String proposerFirstName;

    @JsonProperty("proposer_middle_name")
    String proposerMiddleName;

    @JsonProperty("proposer_full_name")
    String proposerFullName;

    @JsonProperty("age_proof")
    String ageProof;

    @JsonProperty("nationality")
    String nationality;

    @JsonProperty("national_id")
    String nationalId;

    @JsonProperty("national_id_expiry_date")
    String nationalIdExpiryDate;

    @JsonProperty("usual_state_of_health")
    String usualStateOfHealth;

    @JsonProperty("exact_nature_of_duties")
    String exactNatureOfDuties;

    @JsonProperty("annual_income_from_all_sources")
    String annualIncomeFromAllSources;

    @JsonProperty("source_of_income_for_payment_of_premium")
    String sourceOfIncomeForPaymentOfPremium;

    @JsonProperty("is_politically_exposed")
    String isPoliticallyExposed;

    @JsonProperty("passport_details")
    PassportDetails passportDetails;

    @JsonProperty("address_of_communication")
    AddressOfCommunication addressOfCommunication;

    public ProposalSubmitRequest() {

    }

    public ProposalSubmitRequest(int age, String dob, String gender, String emailId, String education, String occupation,
            String customerId, String fatherName, String employerName, String maritalStatus, String placeOfBirth,
            String lengthOfService, String proposerLastName, String proposerFirstName, String proposerMiddleName,
            String proposerFullName, String ageProof, String nationality, String nationalId,
            String nationalIdExpiryDate, String usualStateOfHealth, String exactNatureOfDuties,
            String annualIncomeFromAllSources, String sourceOfIncomeForPaymentOfPremium, String isPoliticallyExposed,
            PassportDetails passportDetails, AddressOfCommunication addressOfCommunication) {
        this.age = age;
        this.dob = dob;
        this.gender = gender;
        this.emailId = emailId;
        this.education = education;
        this.occupation = occupation;
        this.customerId = customerId;
        this.fatherName = fatherName;
        this.employerName = employerName;
        this.maritalStatus = maritalStatus;
        this.placeOfBirth = placeOfBirth;
        this.lengthOfService = lengthOfService;
        this.proposerLastName = proposerLastName;
        this.proposerFirstName = proposerFirstName;
        this.proposerMiddleName = proposerMiddleName;
        this.proposerFullName = proposerFullName;
        this.ageProof = ageProof;
        this.nationality = nationality;
        this.nationalId = nationalId;
        this.nationalIdExpiryDate = nationalIdExpiryDate;
        this.usualStateOfHealth = usualStateOfHealth;
        this.exactNatureOfDuties = exactNatureOfDuties;
        this.annualIncomeFromAllSources = annualIncomeFromAllSources;
        this.sourceOfIncomeForPaymentOfPremium = sourceOfIncomeForPaymentOfPremium;
        this.isPoliticallyExposed = isPoliticallyExposed;
        this.passportDetails = passportDetails;
        this.addressOfCommunication = addressOfCommunication;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducation() {
        return education;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setLengthOfService(String lengthOfService) {
        this.lengthOfService = lengthOfService;
    }

    public String getLengthOfService() {
        return lengthOfService;
    }

    public void setProposerLastName(String proposerLastName) {
        this.proposerLastName = proposerLastName;
    }

    public String getProposerLastName() {
        return proposerLastName;
    }

    public void setProposerFirstName(String proposerFirstName) {
        this.proposerFirstName = proposerFirstName;
    }

    public String getProposerFirstName() {
        return proposerFirstName;
    }

    public void setProposerMiddleName(String proposerMiddleName) {
        this.proposerMiddleName = proposerMiddleName;
    }

    public String getProposerMiddleName() {
        return proposerMiddleName;
    }

    public void setProposerFullName(String proposerFullName) {
        this.proposerFullName = proposerFullName;
    }

    public String getProposerFullName() {
        return proposerFullName;
    }

    public void setAgeProof(String ageProof) {
        this.ageProof = ageProof;
    }

    public String getAgeProof() {
        return ageProof;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalIdExpiryDate(String nationalIdExpiryDate) {
        this.nationalIdExpiryDate = nationalIdExpiryDate;
    }

    public String getNationalIdExpiryDate() {
        return nationalIdExpiryDate;
    }

    public void setUsualStateOfHealth(String usualStateOfHealth) {
        this.usualStateOfHealth = usualStateOfHealth;
    }

    public String getUsualStateOfHealth() {
        return usualStateOfHealth;
    }

    public void setExactNatureOfDuties(String exactNatureOfDuties) {
        this.exactNatureOfDuties = exactNatureOfDuties;
    }

    public String getExactNatureOfDuties() {
        return exactNatureOfDuties;
    }

    public void setAnnualIncomeFromAllSources(String annualIncomeFromAllSources) {
        this.annualIncomeFromAllSources = annualIncomeFromAllSources;
    }

    public String getAnnualIncomeFromAllSources() {
        return annualIncomeFromAllSources;
    }

    public void setSourceOfIncomeForPaymentOfPremium(String sourceOfIncomeForPaymentOfPremium) {
        this.sourceOfIncomeForPaymentOfPremium = sourceOfIncomeForPaymentOfPremium;
    }

    public String getSourceOfIncomeForPaymentOfPremium() {
        return sourceOfIncomeForPaymentOfPremium;
    }

    public void setIsPoliticallyExposed(String isPoliticallyExposed) {
        this.isPoliticallyExposed = isPoliticallyExposed;
    }

    public String getIsPoliticallyExposed() {
        return isPoliticallyExposed;
    }

    public void setPassportDetails(PassportDetails passportDetails) {
        this.passportDetails = passportDetails;
    }

    public PassportDetails getPassportDetails() {
        return passportDetails;
    }

    public void setAddressOfCommunication(AddressOfCommunication addressOfCommunication) {
        this.addressOfCommunication = addressOfCommunication;
    }

    public AddressOfCommunication getAddressOfCommunication() {
        return addressOfCommunication;
    }

}

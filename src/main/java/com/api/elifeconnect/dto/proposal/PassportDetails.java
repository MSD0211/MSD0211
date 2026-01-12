package com.api.elifeconnect.dto.proposal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PassportDetails {
    @JsonProperty("passport_number")
    private String passportNumber;
    @JsonProperty("date_of_expiry")
    private String dateOfExpiry;
    @JsonProperty("date_of_issue")
    private String dateOfIssue;
    @JsonProperty("country_of_issue")
    private String countryOfIssue;

    public PassportDetails()
    {
     
    }
    public PassportDetails(String passportNumber, String dateOfExpiry, String dateOfIssue, String countryOfIssue) {
        this.passportNumber = passportNumber;
        this.dateOfExpiry = dateOfExpiry;
        this.dateOfIssue = dateOfIssue;
        this.countryOfIssue = countryOfIssue;
    }

    public String getPassportNumber() {
        return this.passportNumber;
    }

    public String getDateOfExpiry() {
        return this.dateOfExpiry;
    }

    public String getDateOfIssue() {
        return this.dateOfIssue;
    }

    public String getCountryOfIssue() {
        return this.countryOfIssue;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }
}


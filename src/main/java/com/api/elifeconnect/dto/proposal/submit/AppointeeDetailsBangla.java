package com.api.elifeconnect.dto.proposal.submit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppointeeDetailsBangla {

    String age;

    String dob;

    String address;

    String relation;

    @JsonProperty("last_name")
    String lastName;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("national_id")
    String nationalId;

    @JsonProperty("nationality")
    String nationality;

    @JsonProperty("passport_number")
    String passportNumber;

    @JsonProperty("local_phone_number")
    String localPhoneNumber;

    @JsonProperty("passport_expiry_date")
    String passportExpiryDate;

    @JsonProperty("national_id_expiry_date")
    String nationalIdExpiryDate;

    public AppointeeDetailsBangla()
    {

    }
    public AppointeeDetailsBangla(String age, String dob, String address, String relation,String lastName, String firstName, String nationalId, String nationality, String passportNumber, String localPhoneNumber, String passportExpiryDate, String nationalIdExpiryDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.nationality = nationality;
        this.age = age;
        this.dob = dob;
        this.relation = relation;
        this.passportNumber = passportNumber;
        this.passportExpiryDate = passportExpiryDate;
        this.nationalId = nationalId;
        this.nationalIdExpiryDate = nationalIdExpiryDate;
        this.localPhoneNumber = localPhoneNumber;
    }


    public void setAge(String age) {
        this.age = age;
    }
    public String getAge() {
        return age;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getDob() {
        return dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
    public String getRelation() {
        return relation;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
    public String getNationalId() {
        return nationalId;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getNationality() {
        return nationality;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setLocalPhoneNumber(String localPhoneNumber) {
        this.localPhoneNumber = localPhoneNumber;
    }
    public String getLocalPhoneNumber() {
        return localPhoneNumber;
    }

    public void setPassportExpiryDate(String passportExpiryDate) {
        this.passportExpiryDate = passportExpiryDate;
    }
    public String getPassportExpiryDate() {
        return passportExpiryDate;
    }

    public void setNationalIdExpiryDate(String nationalIdExpiryDate) {
        this.nationalIdExpiryDate = nationalIdExpiryDate;
    }
    public String getNationalIdExpiryDate() {
        return nationalIdExpiryDate;
    }

}

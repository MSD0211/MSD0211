package com.api.elifeconnect.dto.proposal.submit;

import com.fasterxml.jackson.annotation.JsonProperty;

   
public class BankDetailsForPaymentOfPremiumBangla {

   String name;

   String branch;

   @JsonProperty("account_number")
   String accountNumber;

   @JsonProperty("routing_number")
   String routingNumber;

    public BankDetailsForPaymentOfPremiumBangla()
    {

    }
    public BankDetailsForPaymentOfPremiumBangla(String name, String branch, String accountNumber, String routingNumber) {
        this.name = name;
        this.branch = branch;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getBranch() {
        return branch;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }
    public String getRoutingNumber() {
        return routingNumber;
    }
    
}

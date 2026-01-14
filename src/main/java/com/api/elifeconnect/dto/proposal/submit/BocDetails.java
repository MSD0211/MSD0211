package com.api.elifeconnect.dto.proposal.submit;

public class BocDetails {
  private String boc_no;
  private String boc_date;
  private String boc_amount;


 // Getter Methods 

  public String getBoc_no() {
    return boc_no;
  }

  public String getBoc_date() {
    return boc_date;
  }

  public String getBoc_amount() {
    return boc_amount;
  }

 // Setter Methods 

  public void setBoc_no( String boc_no ) {
    this.boc_no = boc_no;
  }

  public void setBoc_date( String boc_date ) {
    this.boc_date = boc_date;
  }

  public void setBoc_amount( String boc_amount ) {
    this.boc_amount = boc_amount;
  }
}


package com.api.elifeconnect.dto.proposal.submit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Mer 
{
    private String bp;
    private String abdomen;
    @JsonProperty("pulse_rate")
    private String pulseRate;
    @JsonProperty("chest_expiration")
    private String chestExpiration;
    @JsonProperty("chest_inspiration")
    private String chestInspiration;

    public Mer()
    {
     
    }
    public Mer(String bp, String abdomen, String pulseRate, String chestExpiration, String chestInspiration) {
        this.bp = bp;
        this.abdomen = abdomen;
        this.pulseRate = pulseRate;
        this.chestExpiration = chestExpiration;
        this.chestInspiration = chestInspiration;
    }

    public String getBp() {
        return this.bp;
    }

    public String getAbdomen() {
        return this.abdomen;
    }

    public String getPulseRate() {
        return this.pulseRate;
    }

    public String getChestExpiration() {
        return this.chestExpiration;
    }

    public String getChestInspiration() {
        return this.chestInspiration;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public void setPulseRate(String pulseRate) {
        this.pulseRate = pulseRate;
    }

    public void setChestExpiration(String chestExpiration) {
        this.chestExpiration = chestExpiration;
    }

    public void setChestInspiration(String chestInspiration) {
        this.chestInspiration = chestInspiration;
    }
}

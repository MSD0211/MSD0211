package com.api.elifeconnect.dto.proposal.submit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadedDocument{
    public String title;
    public String filename;
    public String subtitle;

    public UploadedDocument()
    {

    }

    public UploadedDocument(String title,String filename,String subtitle){
        this.title = title;
        this.filename = filename; 
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    public String getFilename() {
        return this.filename;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFilename(String fileName) {
        this.filename = fileName;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}

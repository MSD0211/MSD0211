package com.api.elifeconnect.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "elife")
public record ElifeProperties(
        Api api
) {
    public record Api(
            String baseurl,
            Loan loan,
            String key,
            String keyHeader
    ) {}

    public record Loan(
            Enquiry enquiry
    ){} 

    public record Enquiry(
            String url
    ){}
}

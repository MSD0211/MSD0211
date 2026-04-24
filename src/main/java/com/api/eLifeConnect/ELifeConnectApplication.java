package com.api.elifeconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.api.elifeconnect.config.ElifeProperties;

@SpringBootApplication
@EnableConfigurationProperties({ElifeProperties.class})
public class ELifeConnectApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ELifeConnectApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ELifeConnectApplication.class, args);
    }
}

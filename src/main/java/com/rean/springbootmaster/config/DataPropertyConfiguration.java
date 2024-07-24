package com.rean.springbootmaster.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataPropertyConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    public String getApplicationName() {
        return applicationName;
    }
}

package com.rean.springbootmaster;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataPropertyConfiguration {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${custom.property}")
    private String customProperty;

    public String getAppName() {
        return appName;
    }

    public String getCustomProperty() {
        return customProperty;
    }
}

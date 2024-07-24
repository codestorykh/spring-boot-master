package com.rean.springbootmaster.config;

import com.rean.springbootmaster.cache.MessageCache;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationComponent {

    @Autowired
    private MessageCache messageCache;

    @PostConstruct
    public void init() {
        System.out.println("ApplicationComponent is initialized");
        messageCache.loadMessage();
    }
}

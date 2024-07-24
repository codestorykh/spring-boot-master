package com.rean.springbootmaster.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MessageCache {
    private final HashMap<String, Object> cache = new HashMap<>();
    public void loadMessage() {
        System.out.println("Loading message from db");
        for(int i = 0; i < 10; i++) {
            cache.put("key" + i, new Object());
        }
    }

    public void clearMessage() {
        System.out.println("Clearing message from cache");
        cache.clear();
    }

    public void updateMessage() {
        System.out.println("Updating message from cache");
        cache.put("key", new Object());
    }

    public void deleteMessage() {
        System.out.println("Deleting message from cache");
        cache.remove("key");
    }

    public void addMessage() {
        System.out.println("Adding message to cache");
        cache.put("key", new Object());
    }
}

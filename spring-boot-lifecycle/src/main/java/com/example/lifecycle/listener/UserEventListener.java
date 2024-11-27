package com.example.lifecycle.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.example.lifecycle.event.UserCreatedEvent;

@Component
public class UserEventListener {
    
    private static final Logger logger = LoggerFactory.getLogger(UserEventListener.class);
    
    @EventListener
    @Order(1)
    public void handleUserCreated(UserCreatedEvent event) {
        logger.info("UserEventListener: Synchronously handling user creation for: {}", 
            event.getUsername());
    }
    
    @EventListener
    @Async
    @Order(2)
    public void handleUserCreatedAsync(UserCreatedEvent event) {
        logger.info("UserEventListener: Asynchronously handling user creation for: {}", 
            event.getUsername());
        // Simulate async processing
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

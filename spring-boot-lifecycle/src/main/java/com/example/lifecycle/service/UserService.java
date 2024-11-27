package com.example.lifecycle.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import jakarta.annotation.PostConstruct;
import com.example.lifecycle.event.UserCreatedEvent;

public class UserService implements ApplicationEventPublisherAware {
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private DatabaseService databaseService;
    
    private ApplicationEventPublisher eventPublisher;
    
    public UserService() {
        logger.info("UserService: Constructor called");
    }
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        logger.info("UserService: ApplicationEventPublisherAware.setApplicationEventPublisher() called");
        this.eventPublisher = eventPublisher;
    }
    
    @PostConstruct
    public void init() {
        logger.info("UserService: @PostConstruct called");
        if (!databaseService.isConnected()) {
            throw new IllegalStateException("Database service not initialized!");
        }
    }
    
    public void createUser(String username) {
        logger.info("UserService: Creating user: {}", username);
        // Create user logic
        eventPublisher.publishEvent(new UserCreatedEvent(this, username));
    }
}

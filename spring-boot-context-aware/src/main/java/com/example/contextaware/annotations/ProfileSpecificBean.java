package com.example.contextaware.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
@Profile("dev")
public class ProfileSpecificBean {
    
    private static final Logger logger = LoggerFactory.getLogger(ProfileSpecificBean.class);

    public ProfileSpecificBean() {
        logger.info("ProfileSpecificBean constructor called");
    }

    @PostConstruct
    public void init() {
        logger.info("ProfileSpecificBean initialized - Development environment specific bean");
    }

    public void performDevOperation() {
        logger.info("Performing development-specific operation");
    }
}

package com.example.lifecycle.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class DatabaseService implements InitializingBean, DisposableBean {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);
    private boolean isConnected;
    
    public DatabaseService() {
        logger.info("DatabaseService: Constructor called");
    }
    
    @PostConstruct
    public void init() {
        logger.info("DatabaseService: @PostConstruct called");
        // Initialize database connection
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("DatabaseService: InitializingBean.afterPropertiesSet() called");
        isConnected = true;
    }
    
    public void customInit() {
        logger.info("DatabaseService: Custom init method called");
        // Additional initialization if needed
    }
    
    public boolean isConnected() {
        return isConnected;
    }
    
    @PreDestroy
    public void cleanup() {
        logger.info("DatabaseService: @PreDestroy called");
        // Cleanup resources
    }
    
    @Override
    public void destroy() throws Exception {
        logger.info("DatabaseService: DisposableBean.destroy() called");
        isConnected = false;
    }
    
    public void customDestroy() {
        logger.info("DatabaseService: Custom destroy method called");
        // Additional cleanup if needed
    }
}

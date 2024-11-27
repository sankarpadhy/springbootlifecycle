package com.example.lifecycle.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class DevDataSource implements DataSource {
    
    private static final Logger logger = LoggerFactory.getLogger(DevDataSource.class);
    private boolean connected;
    
    @PostConstruct
    public void init() {
        logger.info("DevDataSource: Initializing development data source");
        connect();
    }
    
    @Override
    public void connect() {
        logger.info("DevDataSource: Connecting to in-memory database");
        connected = true;
    }
    
    @Override
    public void disconnect() {
        logger.info("DevDataSource: Disconnecting from in-memory database");
        connected = false;
    }
    
    @Override
    public boolean isConnected() {
        return connected;
    }
    
    @PreDestroy
    public void cleanup() {
        logger.info("DevDataSource: Cleaning up development data source");
        disconnect();
    }
}

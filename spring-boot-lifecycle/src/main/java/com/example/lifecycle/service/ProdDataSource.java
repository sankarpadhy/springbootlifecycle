package com.example.lifecycle.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class ProdDataSource implements DataSource {
    
    private static final Logger logger = LoggerFactory.getLogger(ProdDataSource.class);
    private boolean connected;
    
    @PostConstruct
    public void init() {
        logger.info("ProdDataSource: Initializing production data source");
        connect();
    }
    
    @Override
    public void connect() {
        logger.info("ProdDataSource: Connecting to production database");
        connected = true;
    }
    
    @Override
    public void disconnect() {
        logger.info("ProdDataSource: Disconnecting from production database");
        connected = false;
    }
    
    @Override
    public boolean isConnected() {
        return connected;
    }
    
    @PreDestroy
    public void cleanup() {
        logger.info("ProdDataSource: Cleaning up production data source");
        disconnect();
    }
}

package com.example.contextaware.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class DependencyBean {
    
    private static final Logger logger = LoggerFactory.getLogger(DependencyBean.class);

    public DependencyBean() {
        logger.info("DependencyBean constructor called");
    }

    @PostConstruct
    public void init() {
        logger.info("DependencyBean @PostConstruct called");
    }

    public void customInit() {
        logger.info("DependencyBean customInit method called");
    }

    @PreDestroy
    public void cleanup() {
        logger.info("DependencyBean @PreDestroy called");
    }

    public void customDestroy() {
        logger.info("DependencyBean customDestroy method called");
    }
}

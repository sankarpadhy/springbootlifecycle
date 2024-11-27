package com.example.contextaware.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

public class DependentBean {
    
    private static final Logger logger = LoggerFactory.getLogger(DependentBean.class);
    
    @Autowired
    private DependencyBean dependencyBean;

    public DependentBean() {
        logger.info("DependentBean constructor called");
    }

    @PostConstruct
    public void init() {
        logger.info("DependentBean initialized with dependencyBean: {}", dependencyBean != null);
    }
}

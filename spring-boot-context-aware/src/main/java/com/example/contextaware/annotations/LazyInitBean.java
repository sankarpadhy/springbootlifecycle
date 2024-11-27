package com.example.contextaware.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;

@Lazy
public class LazyInitBean {
    
    private static final Logger logger = LoggerFactory.getLogger(LazyInitBean.class);

    public LazyInitBean() {
        logger.info("LazyInitBean constructor called");
    }

    @PostConstruct
    public void init() {
        logger.info("LazyInitBean initialized");
    }

    public void performOperation() {
        logger.info("LazyInitBean operation performed");
    }
}

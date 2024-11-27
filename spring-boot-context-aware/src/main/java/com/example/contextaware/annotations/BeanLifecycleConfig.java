package com.example.contextaware.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanLifecycleConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(BeanLifecycleConfig.class);

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public DependencyBean dependencyBean() {
        logger.info("Creating DependencyBean");
        return new DependencyBean();
    }

    @Bean
    @DependsOn("dependencyBean")
    public DependentBean dependentBean() {
        logger.info("Creating DependentBean");
        return new DependentBean();
    }

    @Bean
    @Lazy
    public LazyInitBean lazyInitBean() {
        logger.info("Creating LazyInitBean");
        return new LazyInitBean();
    }
}

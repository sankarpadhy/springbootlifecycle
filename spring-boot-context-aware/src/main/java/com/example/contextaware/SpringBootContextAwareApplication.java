package com.example.contextaware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class SpringBootContextAwareApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(SpringBootContextAwareApplication.class);

    public static void main(String[] args) {
        logger.info("=== Starting Spring Boot Context Aware Demo ===");
        SpringApplication.run(SpringBootContextAwareApplication.class, args);
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

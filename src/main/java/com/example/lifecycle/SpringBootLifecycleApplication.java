package com.example.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Main Spring Boot Application class demonstrating the application lifecycle.
 * This class serves as the entry point and configuration hub for the application.
 * 
 * The Spring Boot lifecycle consists of several phases:
 * 1. Application Context Initialization
 * 2. Bean Instantiation
 * 3. Bean Post-Processing
 * 4. Application Running
 * 5. Shutdown
 */
@SpringBootApplication
public class SpringBootLifecycleApplication implements ApplicationRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(SpringBootLifecycleApplication.class);

    public static void main(String[] args) {
        logger.info("=== Starting Spring Boot Lifecycle Demo ===");
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootLifecycleApplication.class, args);
        
        // Log application startup completion
        logger.info("=== Spring Boot Lifecycle Demo Started ===");
        logger.info("Application Context ID: {}", context.getId());
        logger.info("Active Profiles: {}", String.join(", ", context.getEnvironment().getActiveProfiles()));
    }

    /**
     * CommandLineRunner bean to demonstrate the application startup sequence.
     * This bean will be executed after the application context is fully initialized.
     * 
     * @return CommandLineRunner instance
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("ApplicationRunner: Executing post-initialization tasks");
        logger.info("Application is fully initialized and running");
        logger.info("All beans are created and post-processed");
        logger.info("Ready to serve requests");
    }

    /**
     * Secondary ApplicationRunner bean to demonstrate additional initialization tasks.
     * This bean will be executed after the application context is fully initialized.
     * 
     * @return ApplicationRunner instance
     */
    @Bean
    public ApplicationRunner secondaryRunner() {
        return args -> {
            logger.info("Secondary ApplicationRunner: Executing additional initialization tasks");
            // Add any secondary initialization logic here
        };
    }
}

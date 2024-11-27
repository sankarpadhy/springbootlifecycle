package com.example.lifecycle.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class demonstrates various bean lifecycle hooks in Spring Boot.
 * It implements multiple lifecycle interfaces and uses annotations to show
 * the complete lifecycle of a Spring bean.
 * 
 * Lifecycle Order:
 * 1. Constructor
 * 2. @PostConstruct
 * 3. InitializingBean.afterPropertiesSet()
 * 4. Custom init method
 * 5. ... Bean is in use ...
 * 6. @PreDestroy
 * 7. DisposableBean.destroy()
 * 8. Custom destroy method
 */
@Component
public class LifecycleBean implements InitializingBean, DisposableBean {
    
    private static final Logger logger = LoggerFactory.getLogger(LifecycleBean.class);
    
    // Thread-safe flag for initialization state
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    
    // Resource simulation
    private Resource resource;
    
    /**
     * Constructor - First step in bean lifecycle
     */
    public LifecycleBean() {
        logger.info("1. LifecycleBean: Constructor called");
        this.resource = new Resource();
    }
    
    /**
     * @PostConstruct - Called after dependency injection is complete
     */
    @PostConstruct
    public void init() {
        logger.info("2. LifecycleBean: @PostConstruct method called");
        try {
            // Simulate resource initialization
            if (initialized.compareAndSet(false, true)) {
                resource.initialize();
                logger.info("Resource initialized successfully");
            }
        } catch (Exception e) {
            logger.error("Failed to initialize resource", e);
            throw new RuntimeException("Resource initialization failed", e);
        }
    }
    
    /**
     * InitializingBean interface method - Called after properties are set
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("3. LifecycleBean: InitializingBean's afterPropertiesSet method called");
        // Validate initialization
        if (!initialized.get()) {
            logger.warn("Bean not properly initialized through @PostConstruct");
        }
    }
    
    /**
     * Business method to demonstrate bean is ready for use
     */
    public void performBusinessLogic() {
        logger.info("LifecycleBean: Executing business logic");
        if (!initialized.get()) {
            throw new IllegalStateException("Bean not properly initialized");
        }
        resource.use();
    }
    
    /**
     * @PreDestroy - Called before bean is destroyed
     */
    @PreDestroy
    public void cleanup() {
        logger.info("4. LifecycleBean: @PreDestroy method called");
        try {
            if (initialized.get()) {
                resource.cleanup();
                logger.info("Resource cleaned up successfully");
            }
        } catch (Exception e) {
            logger.error("Error during resource cleanup", e);
        } finally {
            initialized.set(false);
        }
    }
    
    /**
     * DisposableBean interface method - Called during bean destruction
     */
    @Override
    public void destroy() throws Exception {
        logger.info("5. LifecycleBean: DisposableBean's destroy method called");
        // Additional cleanup if needed
    }
    
    /**
     * Context event listeners
     */
    @EventListener
    public void handleContextRefreshed(ContextRefreshedEvent event) {
        logger.info("ContextRefreshedEvent received - Application context is ready");
    }
    
    @EventListener
    public void handleContextClosed(ContextClosedEvent event) {
        logger.info("ContextClosedEvent received - Application context is closing");
    }
    
    /**
     * Inner Resource class to simulate resource management
     */
    private static class Resource {
        private final Logger logger = LoggerFactory.getLogger(Resource.class);
        private boolean initialized = false;
        
        void initialize() {
            logger.info("Initializing resource");
            initialized = true;
        }
        
        void use() {
            if (!initialized) {
                throw new IllegalStateException("Resource not initialized");
            }
            logger.info("Using resource");
        }
        
        void cleanup() {
            logger.info("Cleaning up resource");
            initialized = false;
        }
    }
}

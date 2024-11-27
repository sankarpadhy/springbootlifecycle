package com.example.lifecycle.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * Demonstrates the complete Spring Bean lifecycle with detailed phase tracking.
 * 
 * Lifecycle Phases:
 * 1. Instantiation Phase:
 *    - Constructor called
 *    - Basic setup performed
 *    - Resource instance created
 * 
 * 2. Dependency Injection Phase:
 *    - Dependencies injected by Spring
 *    - Properties set
 * 
 * 3. Initialization Phase:
 *    - @PostConstruct executed
 *    - InitializingBean.afterPropertiesSet() called
 *    - Resource initialized
 * 
 * 4. Ready Phase:
 *    - ContextRefreshedEvent received
 *    - Bean fully initialized and ready
 * 
 * 5. Usage Phase:
 *    - Business methods available
 *    - Resource operations tracked
 * 
 * 6. Destruction Phase:
 *    - @PreDestroy executed
 *    - DisposableBean.destroy() called
 *    - Resource cleanup performed
 */
@Component
public class LifecycleBean implements InitializingBean, DisposableBean {
    
    private static final Logger logger = LoggerFactory.getLogger(LifecycleBean.class);
    private Resource resource;
    private boolean initialized;
    private boolean contextRefreshed;

    /**
     * Phase 1: Instantiation
     * - Creates the bean instance
     * - Initializes basic properties
     * - Creates resource instance
     */
    public LifecycleBean() {
        logger.info("1. LifecycleBean: Constructor called");
        resource = new Resource();
    }

    /**
     * Phase 3: Initialization (Part 1)
     * - Called after dependency injection
     * - Initializes the resource
     * - Sets up initial state
     */
    @PostConstruct
    public void init() {
        logger.info("2. LifecycleBean: @PostConstruct method called");
        try {
            resource.initialize();
            logger.info("Resource initialized successfully");
            initialized = true;
        } catch (Exception e) {
            logger.error("Failed to initialize resource", e);
            throw e;
        }
    }

    /**
     * Phase 3: Initialization (Part 2)
     * - Called after @PostConstruct
     * - Performs additional initialization if needed
     * - Validates initialization state
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("3. LifecycleBean: InitializingBean's afterPropertiesSet method called");
    }

    /**
     * Phase 4: Ready
     * - Called when ApplicationContext is fully initialized
     * - Marks bean as ready for service
     */
    @EventListener
    public void handleContextRefreshed(ContextRefreshedEvent event) {
        logger.info("ContextRefreshedEvent received - Application context is ready");
        contextRefreshed = true;
    }

    /**
     * Phase 5: Usage
     * - Business method demonstrating bean functionality
     * - Tracks resource usage
     */
    public void performOperation() {
        logger.info("LifecycleBean: Executing business logic");
        resource.use();
    }

    /**
     * State tracking: Initialization state
     * @return true if bean is properly initialized
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * State tracking: Context state
     * @return true if application context is refreshed
     */
    public boolean isContextRefreshed() {
        return contextRefreshed;
    }

    /**
     * State tracking: Resource usage
     * @return true if resource has been used
     */
    public boolean isResourceUsed() {
        return resource.isUsed();
    }

    /**
     * State tracking: Resource cleanup
     * @return true if resource has been closed
     */
    public boolean isResourceClosed() {
        return resource.isClosed();
    }

    /**
     * Phase 6: Destruction (Part 1)
     * - Called during bean destruction
     * - Performs resource cleanup
     */
    @PreDestroy
    public void cleanup() {
        logger.info("4. LifecycleBean: @PreDestroy method called");
        resource.close();
    }

    /**
     * Phase 6: Destruction (Part 2)
     * - Final cleanup operations
     * - Called after @PreDestroy
     */
    @Override
    public void destroy() throws Exception {
        logger.info("5. LifecycleBean: DisposableBean's destroy method called");
    }

    /**
     * Inner Resource class demonstrating resource lifecycle management
     */
    private static class Resource {
        private final Logger logger = LoggerFactory.getLogger(Resource.class);
        private boolean initialized;
        private boolean used;
        private boolean closed;

        /**
         * Initializes the resource
         * Called during bean initialization phase
         */
        void initialize() {
            logger.info("Initializing resource");
            initialized = true;
        }

        /**
         * Uses the resource
         * Called during usage phase
         */
        void use() {
            logger.info("Using resource");
            used = true;
        }

        /**
         * Closes the resource
         * Called during destruction phase
         */
        void close() {
            logger.info("Closing resource");
            closed = true;
        }

        boolean isUsed() {
            return used;
        }

        boolean isClosed() {
            return closed;
        }
    }
}

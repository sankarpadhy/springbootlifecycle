package com.example.lifecycle.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for LifecycleBean demonstrating all lifecycle phases
 */
@SpringBootTest
@ActiveProfiles("test")
class LifecycleBeanTest {

    @Autowired
    private LifecycleBean lifecycleBean;

    /**
     * Test Phase 3: Initialization
     * Verifies that bean is properly initialized after construction and @PostConstruct
     */
    @Test
    void testInitialization() {
        assertTrue(lifecycleBean.isInitialized(), "Bean should be initialized after construction");
    }

    /**
     * Test Phase 4: Ready
     * Verifies that context refresh event is received
     */
    @Test
    void testContextRefreshed() {
        assertTrue(lifecycleBean.isContextRefreshed(), "Context should be refreshed");
    }

    /**
     * Test Phase 5: Usage
     * Verifies business operations and resource usage
     */
    @Test
    void testResourceUsage() {
        // First operation
        lifecycleBean.performOperation();
        assertTrue(lifecycleBean.isResourceUsed(), "Resource should be marked as used after operation");

        // Second operation
        lifecycleBean.performOperation();
        assertTrue(lifecycleBean.isResourceUsed(), "Resource should still be marked as used");
    }

    /**
     * Test Phase 6: Destruction
     * Verifies cleanup operations
     */
    @Test
    void testDestruction() {
        // Trigger some operations
        lifecycleBean.performOperation();
        
        // Manually trigger cleanup for testing
        lifecycleBean.cleanup();
        
        assertTrue(lifecycleBean.isResourceClosed(), "Resource should be closed after cleanup");
    }
}

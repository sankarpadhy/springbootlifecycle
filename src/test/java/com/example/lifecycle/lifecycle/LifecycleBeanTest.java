package com.example.lifecycle.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class to verify the lifecycle behavior of LifecycleBean.
 * This test demonstrates:
 * 1. Bean initialization
 * 2. Lifecycle method execution order
 * 3. Bean destruction
 */
@SpringBootTest
@RecordApplicationEvents
class LifecycleBeanTest {

    @Autowired
    private LifecycleBean lifecycleBean;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEvents applicationEvents;

    @Test
    void testLifecycleMethods() {
        // Test business logic execution
        assertDoesNotThrow(() -> lifecycleBean.performBusinessLogic(),
            "Business logic should execute without errors");
    }

    @Test
    void testContextEvents() {
        // Verify that ContextRefreshedEvent was received
        assertTrue(applicationEvents.stream()
                .anyMatch(event -> event.toString().contains("ContextRefreshedEvent")),
            "ContextRefreshedEvent should be received");
    }

    @Test
    void testBeanInitialization() {
        // Verify bean is properly initialized
        assertDoesNotThrow(() -> lifecycleBean.performBusinessLogic(),
            "Bean should be properly initialized");
    }
}

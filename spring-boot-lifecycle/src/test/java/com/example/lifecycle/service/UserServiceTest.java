package com.example.lifecycle.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class UserServiceTest {

    @Autowired
    private UserService userService;
    
    @Autowired
    private DatabaseService databaseService;
    
    @Test
    void whenContextLoads_thenServicesAreInitialized() {
        assertThat(userService).isNotNull();
        assertThat(databaseService).isNotNull();
        assertThat(databaseService.isConnected()).isTrue();
    }
    
    @Test
    void whenCreateUser_thenEventsArePublished() {
        // This will trigger both sync and async event handlers
        userService.createUser("testUser");
        // Add assertions as needed
    }
}

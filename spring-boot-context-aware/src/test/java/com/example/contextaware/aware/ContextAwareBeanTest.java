package com.example.contextaware.aware;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ContextAwareBeanTest {

    @Autowired
    private ContextAwareBean contextAwareBean;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    void whenGetWelcomeMessage_thenReturnsFormattedMessage() {
        String message = contextAwareBean.getWelcomeMessage("Test User");
        assertThat(message).contains("Test User");
    }

    @Test
    void whenGetServerPort_thenReturnsConfiguredPort() {
        String port = contextAwareBean.getServerPort();
        assertThat(port).isEqualTo("8082");
    }

    @Test
    void whenCheckResourceExists_thenReturnsTrue() {
        boolean exists = contextAwareBean.resourceExists("classpath:application.properties");
        assertThat(exists).isTrue();
    }

    @Test
    void whenGetActiveProfiles_thenReturnsProfiles() {
        String[] profiles = contextAwareBean.getActiveProfiles();
        assertThat(profiles).isNotNull();
    }
}

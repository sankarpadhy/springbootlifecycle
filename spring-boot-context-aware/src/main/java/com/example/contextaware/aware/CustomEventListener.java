package com.example.contextaware.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener {
    private static final Logger logger = LoggerFactory.getLogger(CustomEventListener.class);

    @EventListener
    public void handleCustomEvent(CustomContextEvent event) {
        logger.info("Received custom event with message: {}", event.getMessage());
    }
}

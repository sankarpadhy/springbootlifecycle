package com.example.contextaware.aware;

import org.springframework.context.ApplicationEvent;

public class CustomContextEvent extends ApplicationEvent {
    private final String message;

    public CustomContextEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

package com.example.contextaware.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ContextAwareBean implements 
        ApplicationContextAware,
        EnvironmentAware, 
        MessageSourceAware,
        ResourceLoaderAware,
        ApplicationEventPublisherAware {

    private static final Logger logger = LoggerFactory.getLogger(ContextAwareBean.class);
    
    private ApplicationContext applicationContext;
    private Environment environment;
    private MessageSource messageSource;
    private ResourceLoader resourceLoader;
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        logger.info("ApplicationContextAware: Context set");
        logger.info("Active profiles: {}", String.join(", ", applicationContext.getEnvironment().getActiveProfiles()));
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        logger.info("EnvironmentAware: Environment set");
        logger.info("Server port: {}", environment.getProperty("server.port"));
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
        logger.info("MessageSourceAware: MessageSource set");
        logger.info("Greeting message: {}", messageSource.getMessage("message.greeting", null, Locale.getDefault()));
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        logger.info("ResourceLoaderAware: ResourceLoader set");
        logger.info("Can load application.properties: {}", 
            resourceLoader.getResource("classpath:application.properties").exists());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        logger.info("ApplicationEventPublisherAware: EventPublisher set");
        publishCustomEvent("Context aware bean initialized!");
    }

    public void publishCustomEvent(String message) {
        CustomContextEvent event = new CustomContextEvent(this, message);
        eventPublisher.publishEvent(event);
    }

    public String getWelcomeMessage(String name) {
        return messageSource.getMessage("message.welcome", new Object[]{name}, Locale.getDefault());
    }

    public String getServerPort() {
        return environment.getProperty("server.port");
    }

    public boolean resourceExists(String location) {
        return resourceLoader.getResource(location).exists();
    }

    public String[] getActiveProfiles() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }
}

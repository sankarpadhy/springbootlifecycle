# Spring Boot Context Aware Demo

This module demonstrates various Spring Framework Aware interfaces and their usage in a Spring Boot application.

## Overview

The project showcases the following Spring Aware interfaces:
- ApplicationContextAware
- EnvironmentAware
- MessageSourceAware
- ResourceLoaderAware
- ApplicationEventPublisherAware

## Key Components

1. `ContextAwareBean`: Demonstrates the implementation of various Aware interfaces
2. `CustomContextEvent`: Custom application event for demonstration
3. `CustomEventListener`: Event listener for handling custom events

## Features

- Access to ApplicationContext
- Environment property access
- Internationalization (i18n) support
- Resource loading capabilities
- Custom event publishing and handling

## Running the Application

```bash
./mvnw spring-boot:run
```

The application will start on port 8082.

## Testing

Run the tests using:

```bash
./mvnw test
```

## Endpoints

- Health Check: http://localhost:8082/actuator/health
- Info: http://localhost:8082/actuator/info

## Configuration

The application can be configured through `application.properties`:
- Server port
- Actuator endpoints
- Custom messages for i18n
- Logging configuration

## Key Concepts Demonstrated

1. **ApplicationContextAware**
   - Access to Spring's ApplicationContext
   - Bean lookup capabilities
   - Environment access

2. **EnvironmentAware**
   - Access to environment properties
   - Profile management
   - Property resolution

3. **MessageSourceAware**
   - Internationalization support
   - Message resolution
   - Locale handling

4. **ResourceLoaderAware**
   - Resource loading capabilities
   - Classpath resource access
   - File system resource access

5. **ApplicationEventPublisherAware**
   - Event publishing capabilities
   - Custom event handling
   - Asynchronous event processing

## Project Structure

```
spring-boot-context-aware/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/contextaware/
│   │   │       ├── aware/
│   │   │       │   ├── ContextAwareBean.java
│   │   │       │   ├── CustomContextEvent.java
│   │   │       │   └── CustomEventListener.java
│   │   │       └── SpringBootContextAwareApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/example/contextaware/
│               └── aware/
│                   └── ContextAwareBeanTest.java
└── pom.xml
```

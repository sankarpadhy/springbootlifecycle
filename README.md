# Spring Boot Lifecycle Learning Project

This project demonstrates Spring Boot's application lifecycle, bean initialization, and lifecycle management through a comprehensive example.

## Prerequisites

- Java 17 or later
- Maven 3.6 or later

## Project Structure

```
spring-boot-lifecycle/
├── src/
│   ├── main/java/com/example/lifecycle/
│   │   ├── SpringBootLifecycleApplication.java
│   │   └── lifecycle/LifecycleBean.java
│   └── resources/
│       └── application.properties
├── pom.xml
└── README.md
```

## Spring Boot Lifecycle Phases

### 1. Application Startup Phase
The application startup process follows these steps:
1. **Bootstrap Phase**: Initial Spring Boot startup
2. **Environment Preparation**: Loading properties and profiles
3. **ApplicationContext Creation**: Creating the Spring context
4. **Bean Definition Loading**: Scanning and loading bean definitions

### 2. Bean Lifecycle Phase

#### 2.1 Core Lifecycle Methods

| Phase | Annotation / Interface | Method | Purpose |
|-------|----------------------|---------|----------|
| Initialization | @PostConstruct | init() | Runs after dependency injection |
| | InitializingBean | afterPropertiesSet() | Ensures properties are set |
| Destruction | @PreDestroy | destroy() | Cleanup before destruction |
| | DisposableBean | destroy() | Programmatic cleanup |

#### 2.2 Bean States
1. Not Exists → Bean definition loaded
2. Instantiated → Constructor called
3. Populated → Dependencies injected
4. Initialized → @PostConstruct and afterPropertiesSet() called
5. In Service → Bean ready for use
6. Destroyed → Cleanup methods called

### 3. Application Events
Spring Boot fires events in the following order:

1. ApplicationStartingEvent
2. ApplicationEnvironmentPreparedEvent
3. ApplicationContextInitializedEvent
4. ApplicationPreparedEvent
5. ApplicationStartedEvent
6. ApplicationReadyEvent
7. ApplicationFailedEvent (if error occurs)

## Building and Running

### 1. Build the Project
```bash
mvn clean package
```

### 2. Run Tests
```bash
mvn test
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

### 4. Access the Application
- Main endpoint: http://localhost:8081
- Health check: http://localhost:8081/actuator/health
- Info endpoint: http://localhost:8081/actuator/info

## Monitoring

The application includes Spring Boot Actuator for monitoring:
- Health check: `/actuator/health`
- Info endpoint: `/actuator/info`

## Key Features

1. Comprehensive Lifecycle Demonstration
   - Bean initialization phases
   - Dependency injection
   - Resource management
   - Cleanup handling

2. Best Practices
   - Thread-safe initialization
   - Proper resource management
   - Comprehensive logging
   - Event handling

3. Testing
   - Unit tests for lifecycle methods
   - Event verification
   - Resource management testing

## Lifecycle Implementation Details

### LifecycleBean
- Demonstrates complete bean lifecycle
- Implements InitializingBean and DisposableBean
- Uses @PostConstruct and @PreDestroy
- Includes thread-safe resource management

### Main Application
- Shows application-level lifecycle events
- Implements ApplicationRunner
- Provides detailed startup logging
- Demonstrates proper context management

## Logging

The application provides detailed logging of:
- Application startup phases
- Bean lifecycle events
- Resource initialization and cleanup
- Context events and state changes

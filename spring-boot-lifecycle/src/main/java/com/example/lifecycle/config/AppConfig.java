package com.example.lifecycle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import com.example.lifecycle.service.*;

@Configuration
public class AppConfig {
    
    @Bean
    public DatabaseService databaseService() {
        return new DatabaseService();
    }
    
    @Bean
    @DependsOn("databaseService")
    public UserService userService() {
        return new UserService();
    }
    
    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        return new DevDataSource();
    }
    
    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
        return new ProdDataSource();
    }
}

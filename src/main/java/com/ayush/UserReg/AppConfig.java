package com.ayush.UserReg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ayush.UserReg.common.ServiceRegistry;
import com.ayush.UserReg.service.CommunicationService;
import com.ayush.UserReg.service.CommunicationServiceImpl;
import com.ayush.UserReg.service.MetadataService;
import com.ayush.UserReg.service.MetadataServiceImpl;
import com.ayush.UserReg.service.UserActivationMonitor;
import com.ayush.UserReg.service.UserRegService;
import com.ayush.UserReg.service.UserRegServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    public MetadataService metadataService(ServiceRegistry registry) {
        return new MetadataServiceImpl(registry);
    }
    
    @Bean
    public UserRegService userRegService(ServiceRegistry registry, UserActivationMonitor activationMonitor) {
        return new UserRegServiceImpl(registry, activationMonitor);
    }
    
    @Bean
    public CommunicationService communicationService(ServiceRegistry registry) {
        return new CommunicationServiceImpl(registry);
    }
    
    @Bean
    public UserActivationMonitor activateMonitorService(ServiceRegistry registry) {
        return new UserActivationMonitor(registry);
    }
    
    @Bean
    public ServiceRegistry serviceRegistry() {
        return new ServiceRegistry();
    }
}

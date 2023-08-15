package com.Bhanu.major.configuration;

import com.Bhanu.major.service.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CategoryService categoryService() {
        return new CategoryService();
    }

    // Other beans and configurations
}

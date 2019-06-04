package com.coco.rzido.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringMvcConfig
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Value("${uploads.resouceImgPath}")
    private String resouceImgPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:///"+resouceImgPath).setCachePeriod(31536000);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
    
}
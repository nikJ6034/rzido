package com.coco.rzido.config;

import com.coco.rzido.config.interceptor.ApiRoleCheckInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptoerConfig implements WebMvcConfigurer{

	@Autowired
	ApiRoleCheckInterceptor apiRoleCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiRoleCheckInterceptor).addPathPatterns("/api/**");
	}
	
}

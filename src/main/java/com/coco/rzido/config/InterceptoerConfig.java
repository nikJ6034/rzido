package com.coco.rzido.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptoerConfig implements WebMvcConfigurer{

	// @Autowired
	// ApiRoleCheckInterceptor apiRoleCheckInterceptor;
	
	// @Override
	// public void addInterceptors(InterceptorRegistry registry) {
	// 	registry.addInterceptor(apiRoleCheckInterceptor).addPathPatterns("/api/**");
	// }
	
}

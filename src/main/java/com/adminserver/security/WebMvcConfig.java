package com.adminserver.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/image/**")
//                .addResourceLocations("file:///C:/project/Pictures/");
                .addResourceLocations("file:///home/ubuntu/Pictures/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://xraydata.site:20400",
                        "http://x-ray.cuboxservice.com",
                        "http://x-ray.cuboxservice.com:5000",
                        "http://x-ray.cuboxservice.com:8080",
                        "http://x-ray.cuboxservice.com:8081",
                        "http://kook-service.kro.kr:3000",
                        "http://localhost:5000",
                        "http://localhost:3000",
                        "http://localhost:3001",
                        "http://localhost:3002")

                // GET, POST, PATCH, DELETE, OPTIONS 메서드 허용
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenProvider())
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}

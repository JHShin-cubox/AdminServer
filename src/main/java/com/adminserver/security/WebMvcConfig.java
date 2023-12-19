/*==================================================================
프로젝트명 : 통합 관리시스템
작성지 : 신정호
작성일 : 2023년 11월 22일
용도 : MVC 설정 및 관리
==================================================================*/

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
//                .addResourceLocations("file:///home/ubuntu/Pictures/");
//                .addResourceLocations("file:///home/cubox/Pictures/");
                .addResourceLocations("file:///home/cubox/image/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://172.16.150.32",
                        "http://172.16.150.34",
                        "http://192.168.0.49",
                        "http://193.168.0.70",
                        "http://localhost:8080",
                        "http://192.168.0.57",
                        "http://xraysite.kr:20400",
                        "http://xraysite.kr:20600",
                        "https://xraydata.site",
                        "http://x-ray.cuboxservice.com/") // 허용하려는 IP 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE")
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

package com.example.firstdemo.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")             // 允许跨域请求的path，支持路径通配符，如：/api/**
                .allowedOrigins("*")                    // 允许发起请求的源
                .allowedHeaders("*")                    // 允許用戶端提交的 Header
                .allowedMethods("GET")                  // 允許用戶端使用的请求方法
                .allowCredentials(false)                // 不允許携带憑證
                .exposedHeaders("X-Auth-Token, X-Foo")  // 允許访问的 Response Header
                .maxAge(3600)                           // 预检缓存一个小时
        ;
    }
}

package com.example.firstdemo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")             // 允许跨域请求的path，支持路径通配符，如：/api/**
                .allowedOrigins("*")                    // 允许发起请求的源
                .allowedHeaders("*")                    // 允许客户端的提交的 Header，通配符 * 可能有浏览器兼容问题
                .allowedMethods("GET")                  // 允许客户端使用的请求方法
                .allowCredentials(false)                // 不允许携带凭证
                .exposedHeaders("X-Auth-Token, X-Foo")  // 允许额外访问的 Response Header
                .maxAge(3600)                           // 预检缓存一个小时
        ;
    }

    /**
     * 默認解析器 其中locale表示默認語言,當請求中未包含語種信息，則設置默認語種
     * 當前默認為TAIWAN, zh_TW
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.TAIWAN); // 设置默认语言
        return resolver;
    }
}

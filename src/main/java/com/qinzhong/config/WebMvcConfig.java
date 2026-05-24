package com.qinzhong.config;

import com.qinzhong.interceptor.DemoAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * 注册拦截器，仅拦截 /api/orders/**
 * */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final DemoAuthInterceptor demoAuthInterceptor;

    public WebMvcConfig(DemoAuthInterceptor demoAuthInterceptor) {
        this.demoAuthInterceptor = demoAuthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoAuthInterceptor)
                .addPathPatterns("/api/orders", "/api/orders/**");
    }
}

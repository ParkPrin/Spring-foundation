package com.example.intercepter.config;

import com.example.intercepter.interceptor.AuthIntercepteor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final AuthIntercepteor authIntercepteor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(authIntercepteor).addPathPatterns("/api/private/*");
        registry.addInterceptor(authIntercepteor);
    }
}

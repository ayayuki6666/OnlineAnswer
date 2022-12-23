package com.shino.onlineanswer.Configuration;

import com.shino.onlineanswer.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/login","/alterpassword","/register","/css/**","/js/**","/error","/");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

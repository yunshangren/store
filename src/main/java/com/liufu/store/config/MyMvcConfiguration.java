package com.liufu.store.config;

import com.liufu.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class MyMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/login.html");
        patterns.add("/web/register.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/user/reg");
        patterns.add("/user/login");

        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns(patterns);
    }
}

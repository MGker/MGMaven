package com.mg.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("api/path/login");
    }
}

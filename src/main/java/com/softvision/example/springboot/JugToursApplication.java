package com.softvision.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class JugToursApplication {

    public static void main(String[] args) {

        SpringApplication.run(JugToursApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilterFilterRegistrationBean() {
        ForwardedHeaderFilter forwardedHeaderFilter = new ForwardedHeaderFilter();
        FilterRegistrationBean<ForwardedHeaderFilter> bean = new FilterRegistrationBean<>(forwardedHeaderFilter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
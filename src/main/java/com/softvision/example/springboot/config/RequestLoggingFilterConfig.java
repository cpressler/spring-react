/*
 * Copyright (C) 2018 Allegiant Travel Company - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.softvision.example.springboot.config;

import com.softvision.example.springboot.logging.RequestResponseLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig {
    private final Integer  length = 10000;

    @Bean
    public RequestResponseLoggingFilter logFilter() {
        RequestResponseLoggingFilter filter
                = new RequestResponseLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(false);
        filter.setIncludeResponseData(false);
        filter.setMaxPayloadLength(length);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("After Request :");
        filter.setIncludeClientInfo(true);
        return filter;
    }

    @SuppressWarnings("unchecked")
    @Bean
    public FilterRegistrationBean loggingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(logFilter());
        registration.addUrlPatterns("/api/groups/*");
        return registration;
    }
}

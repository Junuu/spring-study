package com.example.study.config

import jakarta.servlet.Filter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig{
    @Bean
    fun authorizationFilterRegistration(authorizationFilter: AuthorizationFilter): FilterRegistrationBean<*> {
        val filterRegistrationBean = FilterRegistrationBean<Filter>()
        filterRegistrationBean.filter = authorizationFilter
        filterRegistrationBean.order = 1
        filterRegistrationBean.addUrlPatterns("/*")
        return filterRegistrationBean
    }

    @Bean
    fun responseRequestLoggingFilterRegistration(responseRequestLoggingFilter: ResponseRequestLoggingFilter): FilterRegistrationBean<*>{
        val filterRegistrationBean = FilterRegistrationBean<Filter>()
        filterRegistrationBean.filter = responseRequestLoggingFilter
        filterRegistrationBean.order = 2
        filterRegistrationBean.addUrlPatterns("/*")
        return filterRegistrationBean
    }
}

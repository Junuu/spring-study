package com.example.study.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
open class SecurityConfig {
    @Bean
    @Order(1)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests {
                it.anyRequest().authenticated()
            }.addFilterBefore(MyAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
        return httpSecurity.build()
    }

    @Bean
    @Order(0)
    fun exceptionSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .securityMatchers {
                it.requestMatchers("/actuator/**")
                    .requestMatchers("/swagger/**")
            }
            .requestCache {
                it.disable()
            }
            .securityContext {
                it.disable()
            }
            .sessionManagement {
                it.disable()
            }
        return http.build()
    }

}


//@Configuration
//class SecurityConfiguration {
//    @Bean
//    fun webSecurityCustomizer(): WebSecurityCustomizer {
//        return WebSecurityCustomizer { web: WebSecurity -> web.ignoring().requestMatchers("/actuator/**", "/swagger/**") }
//    }
//}




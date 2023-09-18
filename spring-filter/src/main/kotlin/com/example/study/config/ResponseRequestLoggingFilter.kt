package com.example.study.config

import com.example.study.log.logger
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.springframework.stereotype.Component

@Component
class ResponseRequestLoggingFilter: Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        logger.info { "request: $request" }
        logger.info { "response: $response" }
        chain.doFilter(request, response)
    }
}

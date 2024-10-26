package com.example.study.filter

import jakarta.servlet.*
import org.slf4j.MDC
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.*


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class TraceIdFilter : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        try {
            // nginx, client 에서 헤더로 넘겨주는 값을 세팅해도 OK
            val traceId = UUID.randomUUID().toString()
            MDC.put("traceId", traceId)

            chain.doFilter(request, response)
        } finally {
            MDC.clear()
        }
    }
}

package com.example.study.config

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.util.PatternMatchUtils
import org.springframework.web.filter.OncePerRequestFilter


/**
 * OncePerRequestFilter
 * Filter
 * RequestWrapper
 * Filter excludeUrlPatterns
 */

@Component
class AuthorizationFilter: OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val requestURI = request.requestURI
        val roleHeader = request.getHeader("role")
        if(isAdminPath(requestURI) && isNotAdmin(roleHeader)){
            throw RuntimeException("현재 권한으로는 접근할 수 없는 uri입니다.")
        }
        chain.doFilter(request, response)
    }

    private fun isAdminPath(requestURI: String): Boolean{
        return PatternMatchUtils.simpleMatch(userCanAccessPaths, requestURI) == false
    }

    private fun isNotAdmin(role: String): Boolean{
        return role != "admin"
    }

    companion object{
        val userCanAccessPaths = listOf("/user-path","/user-path1","/user-path2").toTypedArray()
    }
}

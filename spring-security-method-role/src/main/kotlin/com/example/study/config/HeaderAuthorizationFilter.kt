package com.example.study.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthorizationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val isFail = runCatching {
            val role = request.getHeader("role").uppercase()
            val authorities: List<GrantedAuthority> = listOf(SimpleGrantedAuthority("$ROLE_PREFIX$role"))
            val authentication: Authentication = UsernamePasswordAuthenticationToken(null, "", authorities)
            SecurityContextHolder.getContext().authentication = authentication

            chain.doFilter(request, response)
        }.isFailure

        if (isFail) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "현재 권한으로는 접근할 수 없는 uri입니다.")
        }
    }

    companion object{
        const val ROLE_PREFIX = "ROLE_"
    }
}


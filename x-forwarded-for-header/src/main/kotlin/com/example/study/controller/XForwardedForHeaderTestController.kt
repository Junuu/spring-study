package com.example.study.controller

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@RestController
class XForwardedForHeaderTestController {

    @GetMapping("/header")
    fun xForwardedForGet(request: HttpServletRequest): String{
        println("--------------IP PRINT START------------------------")
        println(getIpAddr())
        println("--------------IP PRINT END------------------------")

        println("--------------COOKIE PRINT END------------------------")
        println(getServiceName() ?: "EMPTY")
        println("--------------COOKIE PRINT END------------------------")
        return getIpAddr() ?: "empty"

    }
    fun getIpAddr(): String? {
        val req = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        var ip = req.getHeader("X-FORWARDED-FOR")
        if (ip == null) ip = req.remoteAddr
        return ip
    }

    private fun getServiceName(): String? {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val cookies: Array<Cookie>? = request.cookies
        println(cookies)
        return cookies?.firstOrNull { it.name == "WV_SERVICE_ID" }?.value
    }
}

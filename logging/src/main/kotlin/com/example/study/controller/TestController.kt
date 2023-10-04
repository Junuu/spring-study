package com.example.study.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/log")
    fun log(@RequestParam id: String, request: HttpServletRequest): String{
        val map = mapOf<Any, Any>("body" to "body", "header" to "header")
        val parameterMap = getParameterMap(request)
        println(parameterMap)
        println(request.parameterMap)

        return "ok"
    }

    private fun getParameterMap(request: HttpServletRequest): Map<String, String>{

        val parameterMap: MutableMap<String, String> = mutableMapOf()
        for ((key, value) in request.parameterMap) {
            parameterMap[key] = value.joinToString(", ")
        }
        return parameterMap
    }
}

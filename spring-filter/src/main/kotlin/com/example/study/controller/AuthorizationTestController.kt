package com.example.study.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorizationTestController {
    @GetMapping("/admin-path")
    fun onlyAdmin(@RequestHeader role: String): String{
        return "접근 가능"
    }

    @GetMapping("/user-path")
    fun onlyUser(@RequestHeader role: String): String{
        return "접근 가능"
    }
}

package com.example.study.controller

import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorizationTestController {

    // @Secured("ROLE_ADMIN") //동작안함
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/admin-path")
    fun onlyAdmin(@RequestHeader role: String): String{
        return "접근 가능"
    }

    @GetMapping("/user-path")
    fun onlyUser(@RequestHeader role: String): String{
        return "접근 가능"
    }
}

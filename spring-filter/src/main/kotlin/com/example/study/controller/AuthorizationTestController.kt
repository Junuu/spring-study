package com.example.study.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseBody
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

    @GetMapping("/path-and-header-logging/{user-id}")
    fun pathVariableAndHeaderLogging(@RequestHeader role: String, @PathVariable("user-id") id: String): ResponseEntity<TestDTO> {
        return ResponseEntity.ok(
            TestDTO(
                role = role,
                id = id,
            )
        )
    }

    @PostMapping("/request-body")
    fun responseEntity(@RequestBody testDTO: TestDTO): String{
        println(testDTO)
        return "ok"
    }

    @GetMapping("/exception-log")
    fun illegalArgumentExceptionLogging() {
        throw IllegalArgumentException("컨트롤러로 들어와서 예외발생")
    }

    @GetMapping("/exception-log-v2")
    fun illegalStateExceptionLogging() {
        throw IllegalStateException("예외 발생")
    }
}

data class TestDTO(
    val id: String,
    val role: String,
)

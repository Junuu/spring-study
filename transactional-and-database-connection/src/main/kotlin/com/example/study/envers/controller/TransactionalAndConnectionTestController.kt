package com.example.study.envers.controller


import com.example.study.envers.service.TestService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TransactionalAndConnectionTestController(
    private val testService: TestService,
) {
    @PostMapping("/transactional")
    fun test(): String{
        testService.test()
        return "ok"
    }
}

package com.example.study.controller


import com.example.study.service.OuterService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TransactionalAndConnectionTestController(
    private val testService: OuterService,
) {
    @PostMapping("/transactional-rollback")
    fun test(): String{
        testService.test()
        return "ok"
    }
}

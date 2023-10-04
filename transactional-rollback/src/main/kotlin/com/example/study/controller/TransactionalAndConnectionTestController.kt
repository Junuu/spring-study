package com.example.study.controller


import com.example.study.service.OuterService
import org.springframework.web.bind.annotation.GetMapping
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

    @GetMapping("/rollback")
    fun test1(): String{
        testService.`의도적으로 예외를 발생시키기`()
        return "ok"
    }
}

package com.example.study.controller


import com.example.study.repository.TestEntity
import com.example.study.repository.TestRepository
import com.example.study.service.OuterService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Thread.sleep


@RestController
class TransactionalAndConnectionTestController(
    private val testService: OuterService,
    private val testRepository: TestRepository,
) {
    @PostMapping("/transactional-outer-call")
    fun test(): String{
        testService.test()
        return "ok"
    }

    @GetMapping("/api-call-data-save")
    fun apiCallDataSave(): String{
        testRepository.save(TestEntity(name = "api-call-data-save"))
        return "api-call-data-save"
    }

    @GetMapping("/api-call")
    fun apiCall(): String{
        sleep(5000)
        return "api-call"
    }
}

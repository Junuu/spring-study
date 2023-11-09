package com.example.study.controller

import com.example.study.service.AddThreadTestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MonitoringTestController(
    private val addThreadTestService: AddThreadTestService,
) {

    @GetMapping("/add-thread")
    fun test(): String{
        repeat(100){
            addThreadTestService.addAsyncThread()
        }
        return "add 30 thread with 1000s sleep"
    }
}

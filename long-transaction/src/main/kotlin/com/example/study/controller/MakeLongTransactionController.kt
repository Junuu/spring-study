package com.example.study.controller

import com.example.study.service.MakeLongTransactionService
import com.example.study.util.MetricUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MakeLongTransactionController(
    private val makeLongTransactionService: MakeLongTransactionService,
) {

    @PostMapping("/long-transaction")
    fun makeLongTransaction(){
        makeLongTransactionService.makeLongTransaction()
    }

    @GetMapping("/ids/{id}")
    fun get(
        @PathVariable id: Long,
    ): String {
        val result = MetricUtils.measureTimeMillis{
            makeLongTransactionService.getId(id)
        }

        println("소요시간 : ${result.second}")

        return result.second.toString()
    }
}
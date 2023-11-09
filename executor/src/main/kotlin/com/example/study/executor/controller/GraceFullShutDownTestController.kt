package com.example.study.executor.controller

import com.example.study.executor.service.AsyncThreadPoolTestService
import com.example.study.log.logger
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GraceFullShutDownTestController(
    private val myExecutor: ThreadPoolTaskExecutor,
    private val asyncThreadPoolTestService: AsyncThreadPoolTestService,
) {
    @GetMapping("/task")
    fun runLongTimeTask(){

        val myTaskProcess = {
            logger.info{"20초 작업 start"}
            Thread.sleep(20000)
            logger.info{"20초 작업 end"}
        }
        myExecutor.submit(myTaskProcess)
    }

    @GetMapping("/stress-test")
    fun test(): String{
        asyncThreadPoolTestService.test()
        return "ok"
    }
}

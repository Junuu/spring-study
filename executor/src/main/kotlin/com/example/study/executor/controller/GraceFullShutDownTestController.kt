package com.example.study.executor.controller

import com.example.study.executor.service.AsyncThreadPoolTestService
import com.example.study.log.logger
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.lang.Thread.sleep
import java.time.LocalTime
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

@RestController
class GraceFullShutDownTestController(
    private val myExecutor: ThreadPoolTaskExecutor,
    private val asyncThreadPoolTestService: AsyncThreadPoolTestService,
    private val scheduledExecutorServiceParallelConfig: ScheduledExecutorService
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

    @GetMapping("/call-before-cancel")
    fun callBeforeCancel(): String{
        sleep(4000)
        println("it is called")
        return "ok"
    }

    @GetMapping("/graceful-cancel")
    fun gracefulCancel(
    ): String{
        val restTemplate = RestTemplate()

        // Job을 스케쥴링합니다.
        println("Scheduled task : " + LocalTime.now())
        val runnable = Runnable {
            println("++ Repeat task : " + LocalTime.now())
            val result = restTemplate.getForEntity<String>("http://localhost:8080/call-before-cancel")
            println(result)
            println("-- Repeat task : " + LocalTime.now())
        }
        val delay = 3L

        val result: ScheduledFuture<*> = scheduledExecutorServiceParallelConfig.scheduleWithFixedDelay(runnable, 2L, delay, TimeUnit.SECONDS)
        println(result.isDone)
        println(result.getDelay(TimeUnit.SECONDS))
        return "finish"
    }
}
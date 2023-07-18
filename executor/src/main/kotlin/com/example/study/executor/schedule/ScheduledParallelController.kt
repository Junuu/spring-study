package com.example.study.executor.schedule

import com.example.study.log.logger
import jakarta.annotation.PostConstruct
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

//@RestController
class ScheduledParallelController(
    private val scheduledExecutorServiceParallelConfig: ScheduledExecutorService,
) {
    @PostConstruct
    fun test(){
        val sleepThreadSecondsTest = Runnable {
            logger.info { "start"}
            Thread.sleep(3000)
            logger.info { "finish"}
        }


        //0.1초 마다 task 생성을 기대한다.
        repeat(
            times = 4,
            action = {scheduledExecutorServiceParallelConfig.scheduleAtFixedRate(
                sleepThreadSecondsTest,
                0,
                100,
                TimeUnit.MILLISECONDS,
            )}
        )
    }

}

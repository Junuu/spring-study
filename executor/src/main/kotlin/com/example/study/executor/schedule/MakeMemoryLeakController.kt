package com.example.study.executor.schedule

import com.example.study.executor.session.MemoryLeakSessionManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

@RestController
class MakeMemoryLeakController(
    private val scheduledExecutorServiceMemoryLeakConfig: ScheduledExecutorService,
    private val memoryLeakSessionManager: MemoryLeakSessionManager,
) {
    @PostMapping("/memory-leak")
    fun generateMemoryLeak(){
        repeat(10_000_000){
            val userId = UUID.randomUUID().toString()
            memoryLeakSessionManager.registerSession(userId)

            //요청을 받으면 스케쥴링은 5초뒤에 시작되고, session을 제거한다.
            scheduledExecutorServiceMemoryLeakConfig.schedule(
                {
                    memoryLeakSessionManager.removeSession(userId)
                }, ScheduledPerformanceController.TIMEOUT_SECONDS, TimeUnit.SECONDS
            )
        }

    }
}

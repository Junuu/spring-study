package com.example.study.executor.schedule

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

@RestController
class ScheduledPerformanceController(
    private val scheduledExecutorServicePerformanceConfig: ScheduledExecutorService,
    private val sessionManager: SessionManager,
) {
    @PostMapping("/performance")
    fun test(){
        val userId = UUID.randomUUID().toString()
        sessionManager.registerSession(userId)

        //요청을 받으면 스케쥴링은 5초뒤에 시작되고, session을 제거한다.
        scheduledExecutorServicePerformanceConfig.schedule(
            {
                sessionManager.removeSession(userId)
            }, TIMEOUT_SECONDS, TimeUnit.SECONDS
        )
    }

    companion object{
        const val TIMEOUT_SECONDS = 5L
    }
}

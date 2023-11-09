package com.example.study.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@Configuration
class ThreadPoolConfig{

    @Bean
    fun myExecutor(): ThreadPoolTaskExecutor{
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 1000
        executor.maxPoolSize = 1000
        executor.queueCapacity = 0
        executor.setThreadNamePrefix("test-task-")
        executor.setWaitForTasksToCompleteOnShutdown(true)
        executor.setAwaitTerminationSeconds(25) // 기본 0초로 설정되어 있음. task 내려갈 때까지 25초 기다리고 앱 종료하도록 설정
        executor.initialize()
        return executor
    }
}

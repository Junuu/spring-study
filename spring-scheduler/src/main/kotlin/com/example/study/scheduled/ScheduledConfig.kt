package com.example.study.scheduled

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@Configuration
class ScheduledConfig {

    @Bean
    fun scheduledThreadPool(): TaskScheduler{
        val taskScheduler = ThreadPoolTaskScheduler()
        taskScheduler.poolSize = 10
        taskScheduler.setThreadNamePrefix("custom-name")
        taskScheduler.setAwaitTerminationSeconds(20)
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true)
        return taskScheduler
    }
}
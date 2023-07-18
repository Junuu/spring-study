package com.example.study.executor.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

@Configuration
class ScheduledThreadPoolConfig {

    @Bean
    fun scheduledExecutorServiceCautionConfig(): ScheduledExecutorService{
        return Executors.newScheduledThreadPool(SCHEDULED_THREAD_POOL_SIZE)
    }

    @Bean
    fun scheduledExecutorServiceParallelConfig(): ScheduledExecutorService{
        return Executors.newScheduledThreadPool(SCHEDULED_THREAD_POOL_SIZE)
    }

    @Bean
    fun scheduledExecutorServicePerformanceConfig(): ScheduledExecutorService{
        return Executors.newScheduledThreadPool(PERFORMANCE_SCHEDULED_THREAD_POOL_SIZE)
    }

    @Bean
    fun scheduledExecutorServiceMemoryLeakConfig(): ScheduledExecutorService{
        return Executors.newScheduledThreadPool(PERFORMANCE_SCHEDULED_THREAD_POOL_SIZE)
    }

    companion object{
        const val SCHEDULED_THREAD_POOL_SIZE = 4
        const val PERFORMANCE_SCHEDULED_THREAD_POOL_SIZE = 10
    }
}

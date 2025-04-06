package com.example.study

import com.example.study.log.logger
import kotlinx.coroutines.*
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component
import java.lang.Thread.sleep

@Component
class CoroutineGracefulShutdown(
    private val myExecutor: ThreadPoolTaskExecutor,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val coroutineDispatcher = myExecutor.asCoroutineDispatcher()

        runBlocking {
            launch(coroutineDispatcher) {
                logger.info { "start" }
                sleep(30000) // 30초 기다림
                logger.info { "end" }
            }
        }
    }
}

@Component
class MyThreadPool{
    @Bean
    fun myExecutor(): ThreadPoolTaskExecutor{
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 10
        executor.maxPoolSize = 10
        executor.queueCapacity = 0
        executor.setThreadNamePrefix("test-task-")
        executor.setWaitForTasksToCompleteOnShutdown(true)
        executor.setAwaitTerminationSeconds(40)
        executor.initialize()
        return executor
    }
}


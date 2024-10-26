package com.example.study.controller

import feign.RequestInterceptor
import kotlinx.coroutines.*
import kotlinx.coroutines.slf4j.MDCContext
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskDecorator
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.lang.Runnable
import java.util.*


@RestController
class LoggingController(
    private val loggingService: LoggingService,
    private val asyncService: AsyncService,
    private val coroutineService: CoroutineService,
    private val testClient: TestClient,
)
{
    @GetMapping("/logging")
    fun loggingController(){
        val traceId = UUID.randomUUID().toString()
        testLogger.info("i am controller traceId: $traceId")
        loggingService.loggingService(traceId)
        asyncService.asyncLogging()
        coroutineService.coroutineLogging()
        testClient.apiCall()
    }

    @GetMapping("/api-call")
    fun apiCall(
        @RequestHeader traceId: String
    ){
        testLogger.info("apiCall traceId: $traceId")
    }
}

@Service
class LoggingService{
    fun loggingService(traceId: String){
        testLogger.info("i am service traceId: $traceId")
    }
}

@Service
class CoroutineService {
    fun coroutineLogging(){
        CoroutineScope(Dispatchers.IO + MDCContext()).launch {
            testLogger.info("i am coroutine service 1")
            delay(1000L)
            testLogger.info("i am coroutine service 2")
        }

        CoroutineScope(Dispatchers.IO + MDCContext()).launch {
            delay(10)
            testLogger.info("i am coroutine service 1")
            testLogger.info("i am coroutine service 2")
        }

    }
}


@Service
class AsyncService {
    @Async
    fun asyncLogging(){
        testLogger.info("i am async service")
    }
}


private val testLogger = LoggerFactory.getLogger("test")

@Configuration
@EnableAsync
class AsyncConfig {
    @Bean
    fun taskExecutor(): TaskExecutor {
        val threadPoolTaskExecutor = ThreadPoolTaskExecutor()
        threadPoolTaskExecutor.corePoolSize = 10

        threadPoolTaskExecutor.setTaskDecorator(MdcTaskDecorator())
        return threadPoolTaskExecutor
    }
}

class MdcTaskDecorator : TaskDecorator {
    override fun decorate(runnable: Runnable): Runnable {
        val copyOfContextMap = MDC.getCopyOfContextMap()
        return Runnable {
            MDC.setContextMap(copyOfContextMap)
            runnable.run()
        }
    }
}

@Configuration
class FeignConfig {
    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate ->
            val traceId = MDC.get("traceId")
            if (traceId != null) {
                requestTemplate.header("traceId", traceId)
            }
        }
    }
}

@FeignClient(name = "testClient", url = "http://localhost:8080")
interface TestClient {
    @GetMapping("/api-call")
    fun apiCall()
}

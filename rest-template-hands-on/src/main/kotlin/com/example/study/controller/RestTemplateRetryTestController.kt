package com.example.study.controller

import com.example.study.log.logger
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.classify.BinaryExceptionClassifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpRequest
import org.springframework.http.ResponseEntity
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.retry.RetryContext
import org.springframework.retry.backoff.FixedBackOffPolicy
import org.springframework.retry.policy.SimpleRetryPolicy
import org.springframework.retry.support.RetryTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.time.Duration
import java.util.*


@RestController
class RestTemplateRetryTestController(
    private val restTemplate: RestTemplate,
    private val retryRestTemplate: RestTemplate,
    private val retryRestTemplateV2: RetryTemplate,
) {

    val url = "http://localhost:8080/time-out-retry-test"
    @GetMapping("time-out-retry")
    fun timeOutRetry(){

        val result = retryCall()
        if(result.isSuccess){
            println("---good---")
        }

        if(result.isFailure){
            logger.warn {result.exceptionOrNull()}
        }
    }

    @GetMapping("time-out-rest-template-retry")
    fun timeOutSpringRetry(){
        retryRestTemplate.getForEntity(url, String::class.java)
    }

    @GetMapping("time-out-rest-template-retry-v2")
    fun timeOutSpringRetryV2(){
        retryRestTemplateV2.execute<ResponseEntity<String>, Throwable> { context ->
            restTemplate.getForEntity(url, String::class.java)
        }
    }

    private fun retryCall(): Result<*> {
        val maxRetires = 3
        for (currentRetry in 1..maxRetires) {
            val result = runCatching {
                restTemplate.getForEntity(url, String::class.java)
            }
            if(result.isSuccess){
                return result
            }
        }
        return Result.failure<ResponseEntity<String>>(Exception("fail"))
    }


    @GetMapping("time-out-retry-test")
    fun timeOutTest(){
        //현재 timeout은 5초이다
        println("----메서드가 호출됩니다----")
//        throw IllegalArgumentException()
        Thread.sleep(6000)
    }
}

@Configuration
class RetryRestTemplateConfig{

    @Bean
    fun retryRestTemplateV2(): RetryTemplate{
        val retryTemplate = RetryTemplate()
        retryTemplate.setRetryPolicy(SimpleRetryPolicy(3))

        //재시도 2초에 한번
        val fixedBackOffPolicy = FixedBackOffPolicy()
        fixedBackOffPolicy.backOffPeriod = 2000L
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy)

        return retryTemplate
    }

    @Bean
    fun retryRestTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate{
        return restTemplateBuilder
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(5))
            .additionalInterceptors(clientHttpRequestInterceptor())
            .build()
    }
    private fun clientHttpRequestInterceptor(): ClientHttpRequestInterceptor {
        return ClientHttpRequestInterceptor { request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution ->

            val retryableExceptions = BinaryExceptionClassifier(
                Collections.singletonMap(IllegalArgumentException::class.java, true), false
            )

            //3회 재시도
            val retryTemplate = RetryTemplate()
            retryTemplate.setRetryPolicy(SimpleRetryPolicy(3,retryableExceptions))

            //재시도 2초에 한번
            val fixedBackOffPolicy = FixedBackOffPolicy()
            fixedBackOffPolicy.backOffPeriod = 2000L
            retryTemplate.setBackOffPolicy(fixedBackOffPolicy)


            try {
                return@ClientHttpRequestInterceptor retryTemplate.execute<ClientHttpResponse, IOException> { context: RetryContext? ->
                    execution.execute(
                        request, body
                    )
                }
            } catch (throwable: Throwable) {
                throw RuntimeException(throwable)
            }
        }
    }
}

package com.example.study.controller

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.lang.Thread.sleep
import java.time.Duration

@RestController
class RestTemplateTimeoutTestController(
    private val restTemplate: RestTemplate,
) {

    @GetMapping("time-out")
    fun timeOut(){
        val url = "http://localhost:8080/time-out-test"
        val newCreateRestTemplate = RestTemplate()
        newCreateRestTemplate.getForEntity(url, String::class.java)
        val responseEntity = restTemplate.getForEntity(url, String::class.java)
        println(responseEntity)
    }

    @GetMapping("time-out-test")
    fun timeOutTest(){
        //1000초 sleep
        sleep(1000000)
    }
}

@Configuration
class RestTemplateConfig{

    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate{
        return restTemplateBuilder
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(5))
            .build()
    }
}

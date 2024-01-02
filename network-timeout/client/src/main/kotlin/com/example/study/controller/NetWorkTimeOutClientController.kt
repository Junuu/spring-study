package com.example.study.controller

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.time.Duration


@RestController
class NetWorkTimeOutClientController(
        private val restTemplate: RestTemplate,
) {

    @PostMapping("/members")
    fun saveMember(){
        val url = "http://localhost:8081/members"
        restTemplate.postForEntity(url, null, String::class.java)
    }
}

@Configuration
class RestTemplateConfig{

    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate{
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(10))
                .build()
    }
}

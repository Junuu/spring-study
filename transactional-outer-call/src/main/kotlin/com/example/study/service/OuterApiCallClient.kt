package com.example.study.service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class OuterApiCallClient(
    private val restTemplate: RestTemplate,
) {
    fun testCall() {
        val apiUrl = "http://localhost:8080/api-call-data-save"
//        val apiUrl = "http://localhost:8080/api-call"
        val response: String = restTemplate.getForObject(apiUrl, String::class.java) ?: "default Value"
        println("Response: $response")
    }
}

@Configuration
class RestTemplateRegister{
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}


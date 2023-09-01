package com.example.study.envers.controller


import com.example.study.log.logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.lang.Thread.sleep


@RestController
class HttpMaxConnectionTestController(
    private val restTemplate: RestTemplate,
) {
    @GetMapping("/long-time")
    fun `10초 걸리는 api를 호출하는 메서드입니다`(): String{
        sleep(5000)
//        val apiUrl = "http://localhost:8081/call-10-sleep-api"
//        val response: String = restTemplate.getForObject(apiUrl, String::class.java) ?: "default Value"
//        println("Response: $response")
        logger.info{"long-time-method-start"}
        return "sorry, i'm late"
    }
}

@Configuration
class RestTemplateConfiguration{
    @Bean
    fun restTemplate(): RestTemplate? {
        return RestTemplate()
    }

}


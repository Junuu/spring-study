package com.example.study.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import java.lang.IllegalArgumentException

@RestController
class RestTemplateErrorHandling {

    val baseUrl = "http://localhost:8080"
    @GetMapping("/error-handle")
    fun errorHandle(): String{
        val apiUrl = "/error-handle-test"
        val restTemplate = RestTemplate()
        try{
            val responseEntity = restTemplate.getForEntity(
                baseUrl + apiUrl,
                String::class.java
            )
            println("----정상 호출 ----")
            println(responseEntity)
            return "성공"
        } catch (e: HttpClientErrorException){
            println("----4xx Http Status 에러가 발생했습니다, 요청값에 문제가 없는지 확인해주세요 ----")
            return "실패"
        } catch (e: HttpServerErrorException){
            println("----5xx Http Status 에러가 발생했습니다 ----")
            return "실패"
        } catch (e: Exception){
            println("---- 예상치 못한 에러입니다, 분석 후 대응이 필요합니다----")
            return "실패"
        }
    }

    @GetMapping("/error-handle-test")
    fun errorHandleTest(){
        throw IllegalArgumentException("호출시 에러가 발생합니디")
    }
}

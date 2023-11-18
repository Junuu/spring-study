package com.example.study.controller

import com.example.study.client.LocalFeignClient
import com.example.study.dto.ClientProfileResponse
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class OuterCallController(
    private val localFeignClient: LocalFeignClient,
) {

    @GetMapping("/outer-call")
    fun `외부 호출용 메서드`(){
        val  response = localFeignClient.getProfiles()
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(kotlinModule())
        val responseBody = objectMapper.readValue(response.body().asInputStream(), ClientProfileResponse::class.java)
        println(responseBody)
    }

    @GetMapping("/outer-call-404")
    fun `404를 반환받는 메서드`(){
        val response = localFeignClient.get404()
        println(response)
    }

    @GetMapping("/outer-call-400")
    fun `400을 반환받는 메서드`(){
        val response = localFeignClient.get400()
        println(response)
    }
}

package com.example.study.controller

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class RestTemplateDeleteRequestTestController {
    val baseUrl = "http://localhost:8080"

    @DeleteMapping("delete-test")
    fun deleteTest() {
        val apiPath = "/delete-test-call/{user-id}/subscription-group/{subscription-group}"
        val restTemplate = RestTemplate()
        val userId = "test"
        val subscriptionGroup = "INAPP"
        val responseEntity = restTemplate.delete(
            baseUrl + apiPath,
            userId, subscriptionGroup
        )
        println(responseEntity)
    }

    @DeleteMapping("delete-test-call/{user-id}/subscription-group/{subscription-group}")
    fun deleteTestCall(
        @PathVariable("subscription-group") subscriptionGroup: String,
        @PathVariable("user-id") userId: String,
    ) {
        println("delete-Test-Called")
        println(userId)
        println(subscriptionGroup)
    }

    @DeleteMapping("delete-exchange-test")
    fun deleteExchangeTest() {
        val apiPath = "/delete-exchange-test-call"
        val restTemplate = RestTemplate()


        val deleteRequest = DeleteRequest()
        val request = HttpEntity(deleteRequest, HttpHeaders())
        val responseEntity = restTemplate.exchange(
            baseUrl + apiPath,
            HttpMethod.DELETE,
            request,
            String::class.java
        )
        println(responseEntity)

    }

    @DeleteMapping("delete-exchange-test-call")
    fun deleteExchangeTestCall(@RequestBody deleteRequest: _DeleteRequest) {
        println(deleteRequest)
    }
}

enum class RequestEnum {
    TEST
}

enum class _RequestEnum {
    TEST
}

data class DeleteRequest(
    val requestEnum: RequestEnum = RequestEnum.TEST,
)

data class _DeleteRequest(
    val requestEnum: _RequestEnum
)

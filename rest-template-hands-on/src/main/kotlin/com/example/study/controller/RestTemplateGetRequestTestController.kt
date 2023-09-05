package com.example.study.controller

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpRequest
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


@RestController
class RestTemplateGetRequestTestController {

    val baseUrl = "http://localhost:8080"

    @GetMapping("/query-test")
    fun getQueryParam(): String {
        val apiPath = "/query-test-call"
        val restTemplate = RestTemplate()
        val url = UriComponentsBuilder
            .fromHttpUrl(baseUrl + apiPath)
            .queryParam("userId", 1)
            .queryParam("id", 2)
            .toUriString()

        val responseEntity = restTemplate.getForEntity(url, String::class.java)
        println(responseEntity.body)
        return "query"
    }

    @GetMapping("/query-test-call")
    fun getQueryParamCall(@RequestParam param: Map<String, String>): String {
        println(param)
        return "query"
    }

    @GetMapping("/path-test")
    fun getPathVariable(): String {
        val apiPath = "/path-test-call/{user-id}"
        val restTemplate = RestTemplate()
        val url = UriComponentsBuilder
            .fromHttpUrl(baseUrl + apiPath)
            .buildAndExpand("1")
            .toUriString()
        val responseEntity = restTemplate.getForEntity(url, String::class.java)
        println(responseEntity.body)
        return "param"
    }

    @GetMapping("/path-test-call/{user-id}")
    fun getPathVariableCall(@PathVariable(value = "user-id") userId: String): String {
        return userId
    }

    @GetMapping("/header-test")
    fun getHeader(): String {
        val apiPath = "/header-test-call"
        val restTemplate = RestTemplate()

        val headers = HttpHeaders()
        headers.set("user-id", "12314")
        headers.set("my-header", "my-header")

        val request: HttpEntity<*> = HttpEntity<Any?>(headers)


        val url = UriComponentsBuilder
            .fromHttpUrl(baseUrl + apiPath)
            .toUriString()
        val responseEntity = restTemplate.exchange(
            url,
            HttpMethod.GET,
            request,
            String::class.java
        )
        return responseEntity.body!!
    }

    @GetMapping("/header-test-call")
    fun getHeaderCall(@RequestHeader(value = "user-id") userId: String): String {
        return userId
    }
}

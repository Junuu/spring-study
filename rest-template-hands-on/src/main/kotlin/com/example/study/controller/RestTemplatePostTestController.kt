package com.example.study.controller

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.net.URI

@RestController
class RestTemplatePostTestController {

    val baseUrl = "http://localhost:8080"

    @PostMapping("post-test")
    fun postTest() {
        val apiPath = "/post-test-call"
        val restTemplate = RestTemplate()
        val person = Person()

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request = HttpEntity<Person>(
            person,
            headers
        )

        val responseEntity = restTemplate.postForEntity(
            baseUrl + apiPath,
            request,
            Person::class.java,
        )
        println(responseEntity.body)
    }

    @PostMapping(
        value = ["post-test-call"],
        consumes = ["application/json"],
        produces = ["application/json"],
    )
    fun postTestCall(@RequestBody person: _Person): ResponseEntity<_Person> {
        println(person)
        return ResponseEntity.ok(
            _Person(
                id = 2,
                name = "changedName",
            )
        )
    }

    @PostMapping("post-location-test")
    fun postLocationTest(){
        val apiPath = "/post-location-test-call"
        val restTemplate = RestTemplate()
        val person = Person()

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request = HttpEntity<Person>(
            person,
            headers
        )

        val uri = restTemplate.postForLocation(
            baseUrl + apiPath,
            request,
            Person::class.java,
        )
        println(uri)
    }

    @PostMapping("post-location-test-call")
    fun postLocationTestCall(): ResponseEntity<Unit>{
//        throw IllegalArgumentException()
        val uri = URI.create("/created-uri")
        return ResponseEntity.created(uri).build()
    }
}

data class Person(
    val id: Int = 1,
    val name: String = "name",
    val extraColumn: String = "test",
)

data class _Person(
    val id: Int,
    val name: String,
)

package com.example.study.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.lang.Thread.sleep

@RestController
class TestController {

    @GetMapping("/test")
    fun test(): String{

        val baseUrl = "http://localhost:8080"
        val apiPath = "/outer-call"
        val restTemplate = RestTemplate()
        val response: ResponseEntity<String> = restTemplate.getForEntity(
            baseUrl + apiPath,
            String::class.java,
        )
        val responseByGetForObject: String? = restTemplate.getForObject(
            baseUrl + apiPath,
            String::class.java,
        )

        println(response)
        println(response.statusCode)
        println(response.headers)
        println(response.body)
        println("-------------------")
        println(responseByGetForObject)

        return "hello"
    }

    @GetMapping("/outer-call")
    fun outerCall(): String{
        sleep(1000)
        return "process 1s job"
    }

    @GetMapping("/object-outer-call")
    fun objectOuterCall(): ResponseEntity<MyObject>{
        sleep(1000)
        return ResponseEntity.ok(MyObject())
    }

    @GetMapping("/nested-object-outer-call")
    fun nestedObjectOuterCall(): ResponseEntity<NestedMyObject>{
        sleep(1000)
        return ResponseEntity.ok(NestedMyObject())
    }

    @GetMapping("/object-test")
    fun objectTest(): String{

        val baseUrl = "http://localhost:8080"
        val apiPath = "/object-outer-call"
        val restTemplate = RestTemplate()
        val response: ResponseEntity<_MyObject> = restTemplate.getForEntity(
            baseUrl + apiPath,
            _MyObject::class.java,
        )
        println(response.statusCode)
        println(response.body)

        return "hello"
    }

    @GetMapping("/nested-object-test")
    fun nestedObjectTest(): String{

        val baseUrl = "http://localhost:8080"
        val apiPath = "/nested-object-outer-call"
        val restTemplate = RestTemplate()
        val response: ResponseEntity<_NestedMyObject> = restTemplate.getForEntity(
            baseUrl + apiPath,
            _NestedMyObject::class.java,
        )
        println(response.statusCode)
        println(response.body)

        return "hello"
    }
}

data class MyObject(
    val col1: String = "col1",
    val col2: String = "col2",
    val col3: String = "col3",
)

data class _MyObject(
    val col2: String = "col2",
)

data class _NestedMyObject(
    val nestedMyObject2: _NestedMyObject2,
    val test: String,
)

data class _NestedMyObject2(
    val nestedCol2: String,
)

data class NestedMyObject(
    val nestedMyObject2: NestedMyObject2 = NestedMyObject2(),
    val test: String = "nestedTest",
)

data class NestedMyObject2(
    val nestedCol1: String = "nestedCol1",
    val nestedCol2: String = "nestedCol2",
)

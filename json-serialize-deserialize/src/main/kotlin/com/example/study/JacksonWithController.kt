package com.example.study

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class JacksonWithController {

    @GetMapping("/jackson-test")
    fun test(): ResponseEntity<Unit>{
//        return ResponseEntity.ok(MyInformation(getName = "김준우", age = 27, email ="bababrll@naver.com"))
        return ResponseEntity.ok().build()
    }

    @PostMapping("/jackson-test2")
    fun test2(
        @RequestBody testRequest: TestRequest,
    ): ResponseEntity<TestRequest>{
        println(testRequest)
        return ResponseEntity.ok(testRequest)
    }

}

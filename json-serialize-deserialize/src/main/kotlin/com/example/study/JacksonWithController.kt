package com.example.study

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JacksonWithController {

    @GetMapping("/jackson-test")
    fun test(): ResponseEntity<Unit>{
//        return ResponseEntity.ok(MyInformation(getName = "김준우", age = 27, email ="bababrll@naver.com"))
        return ResponseEntity.ok().build()
    }
}

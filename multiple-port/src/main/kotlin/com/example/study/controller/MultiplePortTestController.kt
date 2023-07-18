package com.example.study.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MultiplePortTestController {

    @GetMapping("/multiple-port")
    fun hello(): String{
        return "hello"
    }
}


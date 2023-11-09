package com.example.study.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RequestParamWithDefaultValue {

    @GetMapping("/test")
    fun test(@RequestParam(defaultValue = "true") flag: Boolean){
        if(flag){
            println("true")
        }else{
            println("false")
        }
    }
}

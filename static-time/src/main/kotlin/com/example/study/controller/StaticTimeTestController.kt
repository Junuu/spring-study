package com.example.study.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class StaticTimeTestController {

    @GetMapping("/time")
    fun getTime(): LocalDateTime{
        return CURRENT_TIME
    }

    /**
     * 당연히 이렇게 쓰면 안됨, static method 이므로 Application Loading 될 때 시간이 정해져서 원하는 의도로 동작안함
     */
    companion object{
        val CURRENT_TIME = LocalDateTime.now()
    }
}
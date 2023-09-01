package com.example.study.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Thread.sleep

@RestController
class OuterCallController {

    @GetMapping("/call-10-sleep-api")
    fun `외부 호출용 메서드 10초 sleep`(): String{
        sleep(10000)
        return "10s ..."
    }
}

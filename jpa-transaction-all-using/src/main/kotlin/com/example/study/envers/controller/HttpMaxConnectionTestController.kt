package com.example.study.envers.controller


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Thread.sleep


@RestController
class HttpMaxConnectionTestController(
) {
    @GetMapping("/long-time")
    fun `5초 걸리는 메서드입니다`(): String{
        sleep(5000)
        return "sorry, i'm late"
    }
}

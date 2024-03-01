package com.example.study.port_adaptor

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
        private val informationPort: InformationPort,
) {
    @GetMapping("/test")
    fun test() = informationPort.getInformation()
}
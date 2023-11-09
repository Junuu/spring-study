package com.example.study.controller

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class KafkaProducerController(
    private val kafkaTemplate: KafkaTemplate<String,Any>
) {

    @GetMapping("/produce-message")
    fun `produce_100_message`(){
        kafkaTemplate.send("test.event", Test())
    }

}

data class Test(
    val col1: String = "123",
    val col2: String = "456",
)

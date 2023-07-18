package com.example.study.controller

import com.example.study.producer.KafkaProducer
import com.example.study.producer.TestDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class KafkaTestController(
    private val kafkaProducer: KafkaProducer,
) {
    @PostMapping("/kafka-produce-consume")
    fun tests(){
        kafkaProducer.send("test")
    }

}

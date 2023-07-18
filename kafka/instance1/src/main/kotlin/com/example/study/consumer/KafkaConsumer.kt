package com.example.study.consumer

import com.example.study.producer.TestDto
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.util.*


//@Component
class KafkaConsumer{
    val logger =  KotlinLogging.logger{}

    @KafkaListener(topics = ["testTopic"], groupId = "#{ T(java.util.UUID).randomUUID().toString() }"
    ,containerFactory = "myContainerFactory")
    fun consume(payload: TestDto, acknowledgment: Acknowledgment) {
        logger.info("Consumer start: $payload")
        acknowledgment.acknowledge()
    }
}

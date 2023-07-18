package com.example.study

import mu.KotlinLogging
import org.springframework.context.annotation.Primary
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch


@Component
@Primary
class KafkaConsumer{
    val logger =  KotlinLogging.logger{}
    val latch = CountDownLatch(1)
    var saveLastPayload: String = "init"

    @KafkaListener(topics = ["testTopic"], groupId = "testGroup")
    fun consume(@Payload payload: String, acknowledgment: Acknowledgment) {
        logger.info("Consumer start: $payload")
        saveLastPayload = payload
        latch.countDown()
        // Process
        acknowledgment.acknowledge()
    }
}

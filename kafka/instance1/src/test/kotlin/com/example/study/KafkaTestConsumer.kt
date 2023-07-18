package com.example.study

import com.example.study.producer.TestDto
import mu.KotlinLogging
import org.springframework.context.annotation.Primary
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch


@Component
@Primary
class KafkaTestConsumer{
    val logger =  KotlinLogging.logger{}
    val latch = CountDownLatch(1)
    var saveLastPayload: TestDto = TestDto("init","init")

    @KafkaListener(topics = ["testTopic"], groupId = "testGroup", containerFactory = "myContainerFactory")
    fun consume(payload: TestDto) {
        logger.info("Consumer start: $payload")
        saveLastPayload = payload
        latch.countDown()
        // Process
    }
}

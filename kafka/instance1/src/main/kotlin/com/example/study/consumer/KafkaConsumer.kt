package com.example.study.consumer

import com.example.study.producer.TestDto
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import java.lang.Thread.sleep


@Component
class KafkaConsumer{
    val logger =  KotlinLogging.logger{}

    @KafkaListener(topics = ["test"], groupId = "test", containerFactory = "myContainerFactory")
    fun consume(payloads: List<TestDto>) {
        logger.info("Consumer start: $payloads")
        sleep(1000)
        logger.info("Consumer end: $payloads")
    }
}


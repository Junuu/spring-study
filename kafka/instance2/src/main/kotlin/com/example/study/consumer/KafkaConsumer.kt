package com.example.study.consumer

import com.example.study.listener.KafkaConsumeEvent
import com.example.study.request.TestDto
import com.example.study.service.KafkaConsumerService
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.lang.Thread.sleep


@Component
class KafkaConsumer(
    private val kafkaConsumerService: KafkaConsumerService,
    private val applicationEventPublisher: ApplicationEventPublisher,
) {
    val logger = KotlinLogging.logger {}

    @KafkaListener(
        topics = ["test"],
        groupId = "#{ T(java.util.UUID).randomUUID().toString() }",
        containerFactory = "myContainerFactory"
    )
    fun consume(
        @Payload payload: TestDto,
        acknowledgment: Acknowledgment,
    ) {
        logger.info("KafkaConsumer start: $payload")
//        sleep(5000)
//        kafkaConsumerService.messageOccurredException()
        applicationEventPublisher.publishEvent(KafkaConsumeEvent(payload.col1))
        logger.info("KafkaConsumer end: $payload")
        acknowledgment.acknowledge()
    }
}

@Component
class KafkaDeadLetterQueueConsumer{
    val logger = KotlinLogging.logger {}

    @KafkaListener(
        topics = ["test.DLT"],
        groupId = "#{ T(java.util.UUID).randomUUID().toString() }",
        containerFactory = "myContainerFactory"
    )
    fun consume(
        @Payload payload: TestDto,
        acknowledgment: Acknowledgment,
    ) {
        logger.info("KafkaDeadLetterQueueConsumer start: $payload")
        acknowledgment.acknowledge()
    }
}






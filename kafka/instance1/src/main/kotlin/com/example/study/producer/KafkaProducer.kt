package com.example.study.producer

import com.example.study.log.logger
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, TestDto>
) {

    fun send(topic: String) {
        repeat(10){
            logger.info {"kafka Producer start"}
            val message = TestDto(col1 = it.toString(), col2 = it.toString())
            logger.info {"topic & payload: $topic , $message"}
            kafkaTemplate.send("test", message)
        }
    }
}

data class TestDto(
    val col1: String,
    val col2: String,
)



package com.example.study.consumer

import com.example.study.controller.Test
import com.example.study.log.logger
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import java.lang.Thread.sleep

@KafkaListener(
    topics = ["test.event"],
    groupId = "test-application",
    containerFactory = "customContainerFactory",
)
@Component
class KafkaConsumer {

    fun test(
        request: Test,
        acknowledgment: Acknowledgment,
    ){
        logger.info {"Consumer Start"}
        sleep(1000)
        logger.info {"Consumer End"}
        acknowledgment.acknowledge()
    }
}

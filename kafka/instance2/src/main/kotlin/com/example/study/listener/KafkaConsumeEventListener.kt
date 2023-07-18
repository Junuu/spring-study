package com.example.study.listener


import com.example.study.log.logger
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.lang.Thread.sleep

@Component
class KafkaConsumeEventListener {

    @EventListener
    @Async
    fun processKafkaConsumeEvent(event: KafkaConsumeEvent){
        logger.info { "processKafkaConsumeEvent start: $event" }
        sleep(5000)
        logger.info { "processKafkaConsumeEvent end: $event" }
    }

}

data class KafkaConsumeEvent(
    val id: String,
)

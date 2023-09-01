package com.example.study.listener

import com.example.study.event.DataSaveEvent
import com.example.study.log.logger
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class DataSaveEventListener {

    @EventListener
//    @Async
    fun test(dataSaveEvent: DataSaveEvent){
        logger.info {"async start"}
        throw RuntimeException()
    }

    @EventListener
    fun notUse(notUseEvent: NotUseEvent){
        println("hello")
    }
}

data class NotUseEvent(
    val id: String,
)



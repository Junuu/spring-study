package com.example.study.listener

import com.example.study.event.AsyncDebugTest
import com.example.study.event.DataSaveEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class TestListener {

    @EventListener
    fun notUse(dataSaveEvent: DataSaveEvent){
        println("i'm not async")
    }

    @EventListener
    @Async
    fun asyncDebug(asyncDebugTest: AsyncDebugTest){
        println(asyncDebugTest)
    }
}

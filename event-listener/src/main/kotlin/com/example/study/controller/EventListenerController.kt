package com.example.study.controller

import com.example.study.event.AsyncDebugTest
import com.example.study.event.SignUpEvent
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationEventPublisher
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class EventListenerController(
    private val applicationEventPublisher: ApplicationEventPublisher,
) {

    @PostMapping("/send-event")
    fun sendEvent(): String {
        println("send-event run")
        applicationEventPublisher.publishEvent(
            SignUpEvent(
                id = UUID.randomUUID().toString()
            )
        )
        return "ok"
    }

    @PostMapping("/async-debug")
    fun asyncDebugTest(): String {
        println("async-debug run")
        applicationEventPublisher.publishEvent(
            AsyncDebugTest(
                id = UUID.randomUUID().toString()
            )
        )
        return "ok"
    }
}

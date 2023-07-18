package com.example.study.controller

import com.example.study.event.SignUpEvent
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
        applicationEventPublisher.publishEvent(
            SignUpEvent(
                id = UUID.randomUUID().toString()
            )
        )
        return "ok"
    }
}

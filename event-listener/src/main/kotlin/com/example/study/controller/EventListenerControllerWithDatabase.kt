package com.example.study.controller

import com.example.study.service.EventWithDatabaseService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EventListenerControllerWithDatabase(
    private val eventWithDatabaseService: EventWithDatabaseService,
) {
    @PostMapping("/send-data-save-event")
    fun test(){
        eventWithDatabaseService.test()
    }
}

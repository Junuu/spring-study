package com.example.study.service

import com.example.study.event.AfterSignUpEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class SignUpEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher,
){

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun publish(){
        applicationEventPublisher.publishEvent(
            AfterSignUpEvent(
                id = UUID.randomUUID().toString()
            )
        )
    }
}

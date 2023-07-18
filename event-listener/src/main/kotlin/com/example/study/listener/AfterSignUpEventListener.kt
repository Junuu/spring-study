package com.example.study.listener

import com.example.study.event.AfterSignUpEvent
import com.example.study.service.SignUpService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AfterSignUpEventListener(
    private val signUpService: SignUpService,
) {
    @EventListener
    @Transactional
    fun throwException(afterSignUpEvent: AfterSignUpEvent){
        signUpService.throwException()
    }
}

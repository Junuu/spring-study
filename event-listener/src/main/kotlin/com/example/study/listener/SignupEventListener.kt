package com.example.study.listener

import com.example.study.event.SignUpEvent
import com.example.study.service.SignUpEventPublisher
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SignupEventListener(
    private val signUpEventPublisher: SignUpEventPublisher,
) {

    @TransactionalEventListener
    @Transactional
    fun signUpEvent(signUpEvent: SignUpEvent){
        signUpEventPublisher.publish()
    }
}

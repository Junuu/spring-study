package com.example.study.controller

import com.example.study.event.AsyncDebugTest
import com.example.study.event.SignUpEvent
import com.example.study.event.TransactionEventListenerEvent
import com.example.study.repository.TestDTO
import com.example.study.repository.TestRepository
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationEventPublisher
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class EventListenerController(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val testRepository: TestRepository,
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

    @PostMapping("/transaction-event-listener")
    @Transactional
    fun transactionEventListenerTest(): String{
        println("transaction-event-listener")
        testRepository.save(TestDTO())
        applicationEventPublisher.publishEvent(
            TransactionEventListenerEvent(
                id = UUID.randomUUID().toString()
            )
        )
        return "ok"
    }
}

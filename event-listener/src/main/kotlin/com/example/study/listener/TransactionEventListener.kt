package com.example.study.listener

import com.example.study.event.TransactionEventListenerEvent
import com.example.study.repository.TestDTO
import com.example.study.repository.TestRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

@Component
class TransactionEventListener(
    private val testRepository: TestRepository,
) {

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Async
    fun afterTransactionCommit(transactionEventListenerEvent: TransactionEventListenerEvent){
        testRepository.save(TestDTO())

        println("afterTransactionCommit 예외 발생전 Thread: ${Thread.currentThread().threadGroup}")
        println(Thread.currentThread())
        throw RuntimeException()
    }
}

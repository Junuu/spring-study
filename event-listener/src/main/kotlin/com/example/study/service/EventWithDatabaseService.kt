package com.example.study.service

import com.example.study.event.DataSaveEvent
import com.example.study.log.logger
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class EventWithDatabaseService(
    private val dataSaveTransactionService: DataSaveTransactionService,
    private val applicationEventPublisher: ApplicationEventPublisher,
) {

    fun test(){
        logger.info {"EventWithDatabaseService start"}
        dataSaveTransactionService.save()
        applicationEventPublisher.publishEvent(DataSaveEvent("1"))
        logger.info {"@Async인 EventListner에서 예외가 발생하면 실행될까?"}
    }
}

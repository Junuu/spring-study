package com.example.study.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class OuterService(
//    private val testRepository: TestRepository,
    private val persistenceLayerService: PersistenceLayerService,
    private val outerApiCallClient: OuterApiCallClient,
) {

//    @Transactional
    fun test() {
        persistenceLayerService.save()
        outerApiCallClient.testCall()
//        throw RuntimeException()
    }
}

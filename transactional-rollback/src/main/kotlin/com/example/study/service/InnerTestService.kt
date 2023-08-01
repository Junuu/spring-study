package com.example.study.service

import com.example.study.log.logger
import com.example.study.repository.TestEntity
import com.example.study.repository.TestRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class InnerTestService(
    private val testRepository: TestRepository,
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional
    fun test() {
        testRepository.save(TestEntity(name = "second save"))
        try {
            testRepository.save(TestEntity(name = "third save"))
            throw RuntimeException()
        } catch (e: RuntimeException) {
            logger.info { "inner service error occure" }
        }
        throw RuntimeException()
    }
}

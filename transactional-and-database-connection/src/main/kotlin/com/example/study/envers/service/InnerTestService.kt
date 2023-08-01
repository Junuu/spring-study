package com.example.study.envers.service

import com.example.study.envers.repository.TestEntity
import com.example.study.envers.repository.TestRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class InnerTestService(
    private val testRepository: TestRepository,
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun test(){
        repeat(10){
            testRepository.save(TestEntity(name = UUID.randomUUID().toString()))
        }
    }
}

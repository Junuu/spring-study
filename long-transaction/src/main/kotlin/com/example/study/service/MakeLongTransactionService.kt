package com.example.study.service

import com.example.study.entity.TestEntity
import com.example.study.repository.TestEntityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Thread.sleep

@Service
class MakeLongTransactionService(
    private val testEntityRepository: TestEntityRepository,
) {

    @Transactional(timeout = 10)
    fun makeLongTransaction() {
        repeat(70){
            testEntityRepository.save(TestEntity())
            sleep(100)
        }
    }

    fun getId(id: Long): TestEntity{
        return testEntityRepository.findById(id).get()
    }
}

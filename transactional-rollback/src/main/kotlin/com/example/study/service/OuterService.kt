package com.example.study.service

import com.example.study.log.logger
import com.example.study.repository.TestEntity
import com.example.study.repository.TestRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OuterService(
    private val testRepository: TestRepository,
    private val innerTestService: InnerTestService,
) {

    @Transactional
    fun test() {
        testRepository.save(TestEntity(name = "firstSave"))
        try{
            innerTestService.test()
        }catch (e: Exception){
            logger.error{"outerService get error"}
        }
    }
}

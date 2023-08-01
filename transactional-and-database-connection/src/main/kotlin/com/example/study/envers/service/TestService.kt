package com.example.study.envers.service

import com.example.study.envers.repository.TestEntity
import com.example.study.envers.repository.TestRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class TestService(
    private val testRepository: TestRepository,
    private val innerTestService: InnerTestService,
) {

    @Transactional
    fun test() {
        testRepository.save(TestEntity(name = UUID.randomUUID().toString()))
        innerTestService.test()
    }
}

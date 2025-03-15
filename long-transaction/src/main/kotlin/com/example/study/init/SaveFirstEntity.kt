package com.example.study.init

import com.example.study.entity.TestEntity
import com.example.study.repository.TestEntityRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class SaveFirstEntity(
    private val testEntityRepository: TestEntityRepository,
): ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        testEntityRepository.save(TestEntity())
    }
}
package com.example.study.service

import com.example.study.repository.TestEntity
import com.example.study.repository.TestRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PersistenceLayerService(
    private val testRepository: TestRepository,
) {
    @Transactional
    fun save(){
        testRepository.save(TestEntity(name = "firstSave"))
    }
}

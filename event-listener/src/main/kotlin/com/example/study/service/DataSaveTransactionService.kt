package com.example.study.service

import com.example.study.repository.TestDTO
import com.example.study.repository.TestRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DataSaveTransactionService(
    private val testRepository: TestRepository,
) {

    fun save(){
        testRepository.save(TestDTO())
    }
}

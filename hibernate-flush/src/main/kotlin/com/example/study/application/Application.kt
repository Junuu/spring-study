package com.example.study.application

import com.example.study.entity.TestEntity
import com.example.study.repository.TestEntityRepository
import jakarta.persistence.EntityManager
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class MakeApplicationBug(
    private val testEntityRepository: TestEntityRepository,
    private val entityManager: EntityManager,
): ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        `이미 적재가 되어 있는 상태 재현`()
        존재하는경우삭제()
        새롭게적재()
    }

    private fun `이미 적재가 되어 있는 상태 재현`() {
        testEntityRepository.save(TestEntity(name = MY_NAME))
    }

    private fun 존재하는경우삭제() {
        val testEntity = testEntityRepository.findByName(name = MY_NAME)
        if(testEntity != null){
            testEntityRepository.deleteById(testEntity.id)
        }
    }

    private fun 새롭게적재(){
        testEntityRepository.save(TestEntity(name = MY_NAME))
    }

    companion object{
        val MY_NAME = "junuu"
    }
}

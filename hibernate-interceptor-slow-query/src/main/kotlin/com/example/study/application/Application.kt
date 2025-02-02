package com.example.study.application

import com.example.study.entity.TestEntity
import com.example.study.repository.TestEntityRepository
import jakarta.persistence.EntityManager
import org.hibernate.Session
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
        val sessionFactory = entityManager.unwrap(Session::class.java).sessionFactory
        val properties = sessionFactory.properties
        println("LOG_QUERIES_SLOWER_THAN_MS: ${properties["hibernate.session.events.log.LOG_QUERIES_SLOWER_THAN_MS"]}")

        save()
    }

    private fun save() {
        testEntityRepository.save(TestEntity(name = MY_NAME))
    }

    companion object{
        val MY_NAME = "junuu"
    }
}

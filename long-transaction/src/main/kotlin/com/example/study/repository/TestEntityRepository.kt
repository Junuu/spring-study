package com.example.study.repository

import com.example.study.entity.TestEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TestEntityRepository: JpaRepository<TestEntity, Long> {
    fun findByName(name: String): TestEntity?
}

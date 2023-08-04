package com.example.study.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TestRepository: JpaRepository<TestEntity, String> {
}

package com.example.study.application.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TestJpaEntityRepository: JpaRepository<TestJpaEntity, UUID> {

}

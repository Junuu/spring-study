package com.example.study.application.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.*

@Table(name = "test_table")
@Entity
class TestJpaEntity(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Enumerated(EnumType.STRING)
    var status: TestStatus = TestStatus.WAITING,

    @Column
    val createdAt: Instant = Instant.now(),
)


enum class TestStatus {
    WAITING,
    READY,
    COMPLETE
}

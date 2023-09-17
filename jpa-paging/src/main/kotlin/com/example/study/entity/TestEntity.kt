package com.example.study.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
class TestEntity(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column
    val name: String = "1",
)

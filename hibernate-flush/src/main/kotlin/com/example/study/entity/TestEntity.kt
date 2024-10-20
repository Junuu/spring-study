package com.example.study.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(
    name = "test_entity",
    uniqueConstraints = [UniqueConstraint(columnNames = ["name"])]
)

class TestEntity(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val name: String = "1",
)

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String = UUID.randomUUID().toString(),
)

package com.example.study.repository

import jakarta.persistence.Entity
import jakarta.persistence.Id


@Entity
class TestEntity (
    @Id
    val name: String
)

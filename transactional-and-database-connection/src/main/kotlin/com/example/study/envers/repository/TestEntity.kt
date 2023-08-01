package com.example.study.envers.repository

import jakarta.persistence.Entity
import jakarta.persistence.Id


@Entity
class TestEntity (
    @Id
    val name: String
)

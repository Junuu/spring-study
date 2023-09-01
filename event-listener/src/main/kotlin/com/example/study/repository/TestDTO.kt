package com.example.study.repository

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class TestDTO {
    @Id
    val id: String = UUID.randomUUID().toString()
}

package com.example.study.domain

import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.Type

@Entity
data class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Type(JsonType::class)
        @Column(name = "histories", columnDefinition = "json")
        val histories: MutableMap<String, String>
) {
        fun addHistories(additionalHistories: List<String>) {
                additionalHistories.forEach {
                        histories[it] = it
                }
        }
}
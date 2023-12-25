package com.example.study.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field


@Document
data class Member(
        @Id
        var id: String? = null,

        @Field("histories")
        val histories: MutableMap<String, String>
) {
        fun addHistories(additionalHistories: List<String>) {
                additionalHistories.forEach {
                        histories[it] = it
                }
        }
}
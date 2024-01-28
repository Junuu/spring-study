package com.example.study.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field


@Document
data class Board(
        @Id
        var id: String? = null,

        @Field("hits")
        var hit: Long = 0,
){
        fun addHits(){
                this.hit += 1
        }
}
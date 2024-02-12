package com.example.study.controller.request

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = DtoA::class, name = "DtoA"),
    JsonSubTypes.Type(value = DtoB::class, name = "DtoB")
)
interface SingleEndPointRequest {
    val type: String
}

data class DtoA(
        val col1: String,
        val col2: String,
        override val type: String = "DtoA",
): SingleEndPointRequest

data class DtoB(
        val col3: String,
        val col4: String,
        val col5: String,
        override val type: String = "DtoB",
): SingleEndPointRequest




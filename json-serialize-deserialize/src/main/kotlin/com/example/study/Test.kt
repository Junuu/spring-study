package com.example.study

import com.fasterxml.jackson.annotation.JsonProperty

data class MyInformation1(
    private val name: String,
    private val age: Int,
    @JsonProperty("email")
    val email: String,
){
}


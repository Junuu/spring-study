package com.example.study.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ClientProfileResponse(
    val profiles: List<Profile>
)


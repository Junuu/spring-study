package com.example.study.entity

import jakarta.persistence.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("stock")
class Stock (
    @Id
    val id: String,
    var stock: Long,
)

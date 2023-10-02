package com.example.study.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RedissonConfig {
    @Value("\${spring.data.redis.host}")
    private val redisHost: String = "127.0.0.1"

    @Value("\${spring.data.redis.port}")
    private val redisPort = 0

    private val REDISSON_HOST_PREFIX = "redis://"

    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config()
        config.useSingleServer().setAddress("$REDISSON_HOST_PREFIX$redisHost:$redisPort")
        return Redisson.create(config)
    }

}

package com.example.study.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.redis.core.script.RedisScript


@Configuration
class LuaScriptConfig {

    @Bean
    fun stockDeceaseScript(): RedisScript<Long>{
        val scriptSource = ClassPathResource("redis-scripts/stockDecrease.lua")
        return RedisScript.of(scriptSource, Long::class.java)
    }

    @Bean
    fun internalExceptionScript(): RedisScript<Unit>{
        val scriptSource = ClassPathResource("redis-scripts/internalException.lua")
        return RedisScript.of(scriptSource)
    }
}

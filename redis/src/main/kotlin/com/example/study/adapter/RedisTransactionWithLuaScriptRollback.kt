package com.example.study.adapter

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.script.RedisScript
import org.springframework.stereotype.Component

@Component
class RedisTransactionWithLuaScriptRollback(
    private val internalExceptionScript: RedisScript<Unit>,
    private val stringRedisTemplate: StringRedisTemplate,
) {

    fun internalException(){
        stringRedisTemplate.execute(
            internalExceptionScript, //resources에 작성된 script
            listOf("testKey"),
            null,
        )
    }
}

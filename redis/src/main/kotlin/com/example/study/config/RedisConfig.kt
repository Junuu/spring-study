package com.example.study.config

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.stereotype.Component

@EnableRedisRepositories(basePackages = ["com.example"])
@Configuration
class RedisConfig{

    @Value("\${spring.data.redis.host}")
    private val redisHost: String = "127.0.0.1"

    @Value("\${spring.data.redis.port}")
    private val redisPort = 0

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(redisHost, redisPort.toInt())
    }


    @Bean
    fun stringRedisTemplate(): StringRedisTemplate {
        val stringRedisTemplate = StringRedisTemplate(redisConnectionFactory())
        stringRedisTemplate.keySerializer = StringRedisSerializer()
        stringRedisTemplate.setEnableTransactionSupport(true)
        return stringRedisTemplate
    }
}



@Component
class LocalRedisDateSetter(
    private val stringRedisTemplate: StringRedisTemplate,
) {

    @PostConstruct
    fun localDataSet() {
        stringRedisTemplate.opsForHash<String, String>().put("productItem", "104", "10")
        val valueOperation = stringRedisTemplate.opsForValue()
        valueOperation.set("test","test")
    }
}

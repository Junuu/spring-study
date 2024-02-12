package com.example.study.controller.config

import com.example.study.controller.request.MyCustomDeserializer
import com.example.study.controller.request.SingleEndPointRequestWithCustomDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class JacksonModuleConfiguration {

    @Bean
    fun simpleModule(): SimpleModule{
        val module = SimpleModule()
        module.addDeserializer(SingleEndPointRequestWithCustomDeserializer::class.java, MyCustomDeserializer())
        return module
    }
}
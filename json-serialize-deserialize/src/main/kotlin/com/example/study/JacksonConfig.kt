package com.example.study

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class JacksonConfig {

    @Bean
    fun objectMapperConfig(): ObjectMapper {
        return ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
//            .registerModule(kotlinModule())
            .registerModule(JavaTimeModule())
//            .registerModule(ParameterNamesModule())
//            .findAndRegisterModules()
    }

    @Bean
    fun jsonCustomizer(): Jackson2ObjectMapperBuilderCustomizer {


        return Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
            builder
                .visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
//                .featuresToEnable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
//                .modules(JavaTimeModule())
//                .modules(kotlinModule())
//                .modules(ParameterNamesModule(), JsonMixinModule(), JsonComponentModule(), Jdk8Module(), JavaTimeModule(), kotlinModule())
//                .modules(JavaTimeModule(), kotlinModule(), kotlinModule())
//                .modulesToInstall{
//                    it.add(MyModule())
//                }

        }
    }
}

@Configuration
class MyModuleConfiguration {

    @Bean
    fun myModule(): Module {
        return MyModule()
    }
}

class MyModule : SimpleModule() {}

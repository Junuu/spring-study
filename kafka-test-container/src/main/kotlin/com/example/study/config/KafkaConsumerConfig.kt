package com.example.study.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer
import org.springframework.kafka.listener.DefaultErrorHandler
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.util.backoff.FixedBackOff

@Configuration
@EnableKafka
class KafkaConsumerConfig {
    @Value("\${spring.kafka.consumer.bootstrap-servers}")
    private lateinit var BOOTSTRAP_ADDRESS: String

    @Value("\${spring.kafka.consumer.auto-offset-reset}")
    private lateinit var AUTO_OFFSET_RESET: String

    @Value("\${spring.kafka.consumer.enable-auto-commit}")
    private lateinit var AUTO_COMMIT: String

    @Bean
    fun customConsumerFactory(): ConsumerFactory<String, Any> {

        val deserializer: JsonDeserializer<Any> = JsonDeserializer()
        deserializer.addTrustedPackages("*")
        deserializer.setUseTypeHeaders(true)

        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = BOOTSTRAP_ADDRESS
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = AUTO_OFFSET_RESET
        props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = AUTO_COMMIT
        return DefaultKafkaConsumerFactory(
            props,
            StringDeserializer(),
            deserializer,
        )
    }

    @Bean
    fun customContainerFactory(
        kafkaTemplate: KafkaTemplate<String, Any>,
        @Qualifier("customConsumerFactory") consumerFactory: ConsumerFactory<String, Any>,
    ): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        val fixedBackOff = FixedBackOff(1000L, 2L)
        val recover = DeadLetterPublishingRecoverer(kafkaTemplate)
        val defaultErrorHandler = DefaultErrorHandler(recover, fixedBackOff)
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
        factory.consumerFactory = consumerFactory
        factory.setCommonErrorHandler(defaultErrorHandler)
        return factory
    }
}

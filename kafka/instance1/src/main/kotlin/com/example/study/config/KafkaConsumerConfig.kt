package com.example.study.config

import com.example.study.producer.TestDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.JsonDeserializer

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
    fun consumerFactory(): ConsumerFactory<String, TestDto> {

        val deserializer: JsonDeserializer<TestDto> = JsonDeserializer(TestDto::class.java)
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = BOOTSTRAP_ADDRESS
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = AUTO_OFFSET_RESET
        props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = AUTO_COMMIT
        props[ConsumerConfig.MAX_POLL_RECORDS_CONFIG] = 10
        props[ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG] = 2000
        props[ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG] = 2000
        return DefaultKafkaConsumerFactory(
            props,
            StringDeserializer(),
            deserializer
        )
    }

    @Bean
    fun myContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, TestDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, TestDto>()
        factory.consumerFactory = consumerFactory()
        factory.containerProperties.ackMode = ContainerProperties.AckMode.BATCH
        factory.isBatchListener = true
        factory.containerProperties.idleBetweenPolls = 3000L
        return factory
    }
}


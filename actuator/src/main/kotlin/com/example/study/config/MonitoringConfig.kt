package com.example.study.config

import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@Configuration
class MonitoringConfig {
    @Bean
    fun metricsCommonTags(@Qualifier("myExecutor") myExecutor: ThreadPoolTaskExecutor): MeterRegistryCustomizer<MeterRegistry> {
        return MeterRegistryCustomizer { registry: MeterRegistry? ->
            Gauge.builder(
                "custom.executor.active.threads",
                myExecutor
            ) { obj: ThreadPoolTaskExecutor ->
                obj.activeCount
                    .toDouble()
            }
                .tags("name", "customTaskExecutor")
                .register(registry!!)
        }
    }
}



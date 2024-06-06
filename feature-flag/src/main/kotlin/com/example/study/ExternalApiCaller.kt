package com.example.study

import com.example.study.FeatureFlag.Companion.TEST_TOGGLE
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

fun interface ExternalApiCaller {
    fun invoke(): Long

    @Component
    class Default: ExternalApiCaller {
        override fun invoke(): Long {
            // 외부 API 호출
            return 1
        }
    }

    @Component
    @Primary
    @Profile("local")
    class Dummy(
            private val featureFlagManager: FeatureFlagManager,
            private val default: Default,
    ): ExternalApiCaller {
        override fun invoke(): Long {
            if(featureFlagManager.isEnabled(TEST_TOGGLE)){
                return 0
            }
            return default.invoke()
        }
    }
}
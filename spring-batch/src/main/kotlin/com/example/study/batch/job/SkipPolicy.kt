package com.example.study.batch.job

import org.springframework.batch.core.step.skip.SkipPolicy
import org.springframework.context.annotation.Configuration


@Configuration
class CustomSkipPolicy : SkipPolicy {

    companion object {
        private const val MAX_SKIP_COUNT = 20
    }

    override fun shouldSkip(throwable: Throwable, skipCount: Long): Boolean {
        if (throwable is IllegalArgumentException && skipCount < MAX_SKIP_COUNT) {
            return true
        }

        return false
    }
}

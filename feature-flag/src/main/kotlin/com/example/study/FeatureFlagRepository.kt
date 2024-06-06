package com.example.study

import org.springframework.data.jpa.repository.JpaRepository

interface FeatureFlagRepository: JpaRepository<FeatureFlag, Long> {
    fun findByKeyName(keyName: String): FeatureFlag?
}
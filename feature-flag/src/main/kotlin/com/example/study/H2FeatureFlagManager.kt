package com.example.study

import org.springframework.stereotype.Component

@Component
class H2FeatureFlagManager(
        private val featureFlagRepository: FeatureFlagRepository,
): FeatureFlagManager {
    override fun setFeature(keyName: String, isEnabled: Boolean) {
        val featureFlag = featureFlagRepository.findByKeyName(keyName)
        if(featureFlag == null){
            featureFlagRepository.save(
                    FeatureFlag(
                            keyName = keyName,
                            enabled = isEnabled
                    ))
            return
        }
        featureFlag.enabled = isEnabled
        featureFlagRepository.save(featureFlag)
    }

    override fun isEnabled(keyName: String): Boolean {
        return featureFlagRepository.findByKeyName(keyName)?.enabled
                ?: return false
    }
}
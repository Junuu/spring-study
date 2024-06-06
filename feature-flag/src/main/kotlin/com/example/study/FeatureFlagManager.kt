package com.example.study

interface FeatureFlagManager {
    fun setFeature(keyName: String, isEnabled : Boolean)
    fun isEnabled(keyName: String): Boolean
}
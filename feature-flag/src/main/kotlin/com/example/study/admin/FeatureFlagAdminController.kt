package com.example.study.admin

import com.example.study.ExternalApiCaller
import com.example.study.H2FeatureFlagManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FeatureFlagAdminController(
        private val h2FeatureFlagManager: H2FeatureFlagManager,
        private val externalApiCaller: ExternalApiCaller,
) {

    @PostMapping("/feature-flag/keys/{keyName}/enabled/{isEnabled}")
    fun setKey(
            @PathVariable keyName: String,
            @PathVariable isEnabled: Boolean,
    ){
        h2FeatureFlagManager.setFeature(keyName = keyName, isEnabled = isEnabled)
    }

    @GetMapping("/feature-flag/keys/{keyName}")
    fun getKeyEnabled(
            @PathVariable keyName: String,
    ) = h2FeatureFlagManager.isEnabled(keyName = keyName)

    @GetMapping("/test")
    fun test(){
        println(externalApiCaller.invoke())
    }
}
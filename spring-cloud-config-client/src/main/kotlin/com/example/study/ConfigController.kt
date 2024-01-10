package com.example.study

import com.example.study.log.logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ConfigController(
        private val localConfig: LocalConfig
) {
    @GetMapping("/config")
    fun loadLocalConfig(): ResponseEntity<Unit> {
        logger.info { localConfig.secret }
        logger.info { localConfig.name }
        return ResponseEntity.ok().build()
    }
}

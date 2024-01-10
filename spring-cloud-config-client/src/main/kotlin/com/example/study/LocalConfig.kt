package com.example.study

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.stereotype.Component

@RefreshScope // 설정 변경된 내용을 actuator를 통해 변경값을 갱신
@Component
class LocalConfig {
    @Value("\${test.secret}")
    val secret: String? = null

    @Value("\${test.name}")
    val name: String? = null
}

package com.example.study.controller

import com.example.study.repository.TestRepository
import org.springframework.stereotype.Component

@Component
class TestController(
    private val testRepository: TestRepository,
) {
}
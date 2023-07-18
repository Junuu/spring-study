package com.example.study.executor.schedule

import com.example.study.executor.session.MemoryLeakSessionManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetSessionCountController(
    private val sessionManager: SessionManager,
    private val memoryLeakSessionManager: MemoryLeakSessionManager,
) {

    @GetMapping("/session-count")
    fun getSessionCount(): Int {
        return sessionManager.getSessionSize()
    }

    @GetMapping("/memory-leak-session-count")
    fun getMemoryLeakSessionCount(): Int {
        return memoryLeakSessionManager.getSessionSize()
    }

}

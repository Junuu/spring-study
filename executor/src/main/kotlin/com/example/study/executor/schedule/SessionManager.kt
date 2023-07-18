package com.example.study.executor.schedule

import com.example.study.log.logger
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class SessionManager {
    private val sessionMap: MutableMap<String, String> = ConcurrentHashMap()

    fun registerSession(userId: String) {
        sessionMap[userId] = userId
        logger.info {"session registered, userId: $userId"}
    }

    fun removeSession(userId: String) {
        sessionMap.remove(userId)
        logger.info {"session removed, userId: $userId"}
    }

    fun getSessionSize(): Int{
        return sessionMap.size
    }
}

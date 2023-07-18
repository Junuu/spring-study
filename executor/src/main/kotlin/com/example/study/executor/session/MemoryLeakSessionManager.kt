package com.example.study.executor.session

import com.example.study.log.logger
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class MemoryLeakSessionManager {
    private val sessionMap: MutableMap<String, MySocket> = ConcurrentHashMap()

    fun registerSession(userId: String) {
        sessionMap[userId] = MySocket(userId = userId)
    }

    fun sessionIsOpen(userId: String): Boolean{
        val isConnected = sessionMap[userId]?.isConnected
        return isConnected ?: false
    }

    fun removeSession(userId: String) {
        sessionMap.remove(userId)
    }

    fun getSessionSize(): Int{
        return sessionMap.size
    }
}

data class MySocket(
    val userId: String,
    val isConnected: Boolean = false,
)

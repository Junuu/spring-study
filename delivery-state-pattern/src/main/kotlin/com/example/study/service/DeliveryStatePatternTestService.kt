package com.example.study.service

import com.example.study.domain.Delivery
import com.example.study.domain.ReadyState
import com.example.study.domain.WaitingState
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import java.util.NoSuchElementException
import java.util.concurrent.ConcurrentHashMap

@Service
class DeliveryStatePatternTestService {

    val concurrentMap = ConcurrentHashMap<String, Delivery>()

    fun changeStatus(
        deliveryId: String,
        invoiceCode: String? = null,
        behavior: String = "nextProcess",
    ): String {
        val delivery = concurrentMap[deliveryId] ?: throw NoSuchElementException()
        when (behavior) {
            "nextProcess" -> delivery.nextProcess(invoiceCode)
            "refund" -> delivery.refund()
            else -> delivery.cancel()
        }
        return delivery.currentState().name
    }

    @PostConstruct
    fun dataSave() {
        concurrentMap["1"] = Delivery(state = WaitingState)
    }
}

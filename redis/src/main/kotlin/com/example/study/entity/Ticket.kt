package com.example.study.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
class Ticket(
    private val name: String,
    private var availableStock: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun sell() {
        validateStockCount()
        availableStock -= 1
    }

    private fun validateStockCount() {
        require(availableStock >= 1)
    }

    fun getAvailableStockCount(): Long{
        return availableStock
    }
}

package com.example.study.service

import com.example.study.adapter.StockAdapter
import org.springframework.stereotype.Service

@Service
class DecreaseStockService(
    private val stockAdapter: StockAdapter,
) {
    fun decrease(){
        stockAdapter.decrease()
    }
}

package com.example.study.hibernate.interceptor

import com.example.study.log.logger
import org.hibernate.Interceptor
import org.hibernate.Transaction
import java.util.concurrent.ConcurrentHashMap

class TransactionTimeInterceptor : Interceptor {

    private val transactionStartTimes = ConcurrentHashMap<Transaction, Long>()

    override fun afterTransactionBegin(tx: Transaction) {
        transactionStartTimes[tx] = System.currentTimeMillis()
    }

    override fun afterTransactionCompletion(tx: Transaction) {
        val startTime = transactionStartTimes.remove(tx) ?: return
        val duration = System.currentTimeMillis() - startTime

        when {
            duration > 10_000 -> logger.warn("⚠️ [SLOW TRANSACTION] 실행 시간: {}ms (10초 초과!)", duration)
            duration > 5_000 -> logger.warn("⚠️ [SLOW TRANSACTION] 실행 시간: {}ms (5초 초과!)", duration)
            duration > 3_000 -> logger.info("✅ [NOTICE] 실행 시간: {}ms (3초 초과)", duration)
            else -> logger.debug("✅ [OK] 실행 시간: {}ms", duration)
        }
    }
}

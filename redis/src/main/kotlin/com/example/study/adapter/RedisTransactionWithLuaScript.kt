package com.example.study.adapter

import com.example.study.log.logger
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.script.RedisScript
import org.springframework.stereotype.Component

@Component
class RedisTransactionWithLuaScript(
    private val stockDecreaseScript: RedisScript<Long>,
    private val stringRedisTemplate: StringRedisTemplate,
) {
    fun luaScriptDeceaseStock() {
        val stockCount = runCatching {
            stringRedisTemplate.execute(
                stockDecreaseScript, //resources에 작성된 script
                listOf(PRODUCT_ITEM_ID_KEY), //keys를 넘긴다 KEY[1]부터 시작
                STOCK_ID //args를 넘긴다 ARGV[1]부터 시작
            )
        }.getOrElse { throw StockEmptyException("재고가 소진되었습니다.") }


        logger.info { "luaScript 호출 후 남은 재고 = $stockCount" }
    }

    companion object {
        const val PRODUCT_ITEM_ID_KEY = "productItem"
        const val STOCK_ID = "104"
    }
}

class StockEmptyException(message: String): RuntimeException(message)

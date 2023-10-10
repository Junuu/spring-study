package com.example.study.adapter

import com.example.study.log.logger
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component


@Component
class StockAdapter(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    fun decrease(){
        val key = PRODUCT_ITEM_ID_KEY
        val stockCount = stringRedisTemplate.opsForHash<String, String>().get(key, STOCK_ID)
            ?: throw NoSuchElementException("해당 id로 조회할 수 없습니다.")
        logger.info { "감소 전 남은 재고 = $stockCount" }

        stringRedisTemplate.execute {
            it.watch(key.toByteArray())
            it.multi()
            val hMGet = it.hashCommands().hMGet(key.toByteArray(), STOCK_ID.toByteArray())
            logger.info { "트랜잭션에서 GET 해오면 Null일까? = $hMGet" }
            val testGetValue = stringRedisTemplate.opsForValue().get("test")
//            logger.info { "트랜잭션에서 GET을 해오면 Null일까? = $testGetValue" }
//            it.hashCommands().hIncrBy(key.toByteArray(), STOCK_ID_TEST.toByteArray(), 1)
            if(stockCount.toLong() < 1){
                throw IllegalStateException("재고는 0미만일 수 없습니다. 재고를 채워주세요")
            }
            //예외가 발생한 후에도 반영될까?
//            it.hashCommands().hIncrBy(key.toByteArray(), STOCK_ID_TEST.toByteArray(), 1)
            it.hashCommands().hIncrBy(key.toByteArray(), STOCK_ID.toByteArray(), -1)
//            throw Exception("예외가 발생하면?")
            return@execute it.exec()
        }
    }

    companion object{
        const val PRODUCT_ITEM_ID_KEY = "productItem"
        const val STOCK_ID = "104"
        const val STOCK_ID_TEST = "105"
    }
}

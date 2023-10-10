package com.example.study.adapter

import com.example.study.log.logger
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisTransactionRollbackTest(
    private val stringRedisTemplate: StringRedisTemplate,
) {

    fun rollbackTest(){
        stringRedisTemplate.opsForHash<String, String>().put(PRODUCT_ITEM_ID_KEY, STOCK_ID_TEST, "1")
        stringRedisTemplate.opsForHash<String, String>().put(PRODUCT_ITEM_ID_KEY1, "1234", "1")
        stringRedisTemplate.opsForHash<String, String>().put(PRODUCT_ITEM_ID_KEY2, "1234", "1")

        val stockCountBeforeTransaction = stringRedisTemplate.opsForHash<String, String>().get(PRODUCT_ITEM_ID_KEY, STOCK_ID_TEST)
            ?: throw NoSuchElementException("해당 id로 조회할 수 없습니다.")
        logger.info { "트랜잭션 호출 전 남은 재고 = $stockCountBeforeTransaction" }

        runCatching {
            stringRedisTemplate.execute{
                it.watch(PRODUCT_ITEM_ID_KEY.toByteArray())
                it.multi()
                it.hashCommands().hIncrBy(PRODUCT_ITEM_ID_KEY2.toByteArray(), "1234".toByteArray(), 5)
                //watch를 걸고 트랜잭션 내부에서 2번 갱신이 가능할까?
                it.hashCommands().hIncrBy(PRODUCT_ITEM_ID_KEY2.toByteArray(), "1234".toByteArray(), 100)

                //재고가 1인데 -5이면 -4가 기대됨
                it.hashCommands().hIncrBy(PRODUCT_ITEM_ID_KEY.toByteArray(), STOCK_ID_TEST.toByteArray(), -5)
                //stringValue에 -1을 수행하면 예외가 발생한다

//                it.hashCommands().hIncrBy(PRODUCT_ITEM_ID_KEY.toByteArray(), "1234".toByteArray(), -1)

                //멀티쓰레드 환경에서 CAS 연산이 충돌되었을 때 1234 키의 증가시키는 것이 몇번 수행될까?
                it.hashCommands().hIncrBy(PRODUCT_ITEM_ID_KEY1.toByteArray(), "1234".toByteArray(), 5)
                //재고가 0인데 -1이면 -1이 기대됨
//                it.hashCommands().hIncrBy(PRODUCT_ITEM_ID_KEY.toByteArray(), STOCK_ID_TEST.toByteArray(), -1)

                return@execute it.exec()
            }
        }

        val stockCountAfterTransaction = stringRedisTemplate.opsForHash<String, String>().get(PRODUCT_ITEM_ID_KEY, STOCK_ID_TEST)
            ?: throw NoSuchElementException("해당 id로 조회할 수 없습니다.")
        logger.info { "트랜잭션 호출 후 남은 재고 = $stockCountAfterTransaction" }

        val stockCountAfterTransaction1 = stringRedisTemplate.opsForHash<String, String>().get(PRODUCT_ITEM_ID_KEY1, "1234")
            ?: throw NoSuchElementException("해당 id로 조회할 수 없습니다.")
        logger.info { "트랜잭션 호출 후 남은 재고1 = $stockCountAfterTransaction1" }

        val stockCountAfterTransaction2 = stringRedisTemplate.opsForHash<String, String>().get(PRODUCT_ITEM_ID_KEY2, "1234")
            ?: throw NoSuchElementException("해당 id로 조회할 수 없습니다.")
        logger.info { "트랜잭션 호출 후 남은 재고2 = $stockCountAfterTransaction2" }


    }

    companion object{
        const val PRODUCT_ITEM_ID_KEY = "productItem"
        const val PRODUCT_ITEM_ID_KEY1 = "productItem1"
        const val PRODUCT_ITEM_ID_KEY2 = "productItem2"
        const val STOCK_ID_TEST = "105"
    }

}

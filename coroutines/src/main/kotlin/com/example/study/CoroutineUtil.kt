package com.example.study

import com.example.study.log.logger
import kotlinx.coroutines.*
import java.util.concurrent.*

fun <T> asyncAwaitWithTimeout(timeMillis: Long, block: suspend CoroutineScope.() -> T): T {
    return runBlocking {
        var job: Deferred<T>? = null
        try {
            withTimeout(timeMillis) {
                job = CoroutineScope(Dispatchers.IO).async {
                    block()
                }
                return@withTimeout job!!.await()
            }
        }catch (e: TimeoutCancellationException){
            logger.info("timeout 발생했음")
            job?.cancelAndJoin()
            logger.info("job isActive: ${job?.isActive}")
            throw e
        }catch (e: Exception){
            throw e
        }
    }
}

val executors = Executors.newSingleThreadExecutor()

fun <T> executeWithTimeoutAndCancellationAsync(timeMillis: Long, block: () -> T): T {
    val result: Future<T> = executors.submit(
            Callable { block() }
    )
    try {
        return result.get(timeMillis, TimeUnit.MILLISECONDS)
    } catch (e: ExecutionException) {
        throw e.cause ?: e
    } catch (e: TimeoutException) {
        logger.info("Future의 타임아웃을 이용해서 쓰레드를 종료시킨다")
        result.cancel(true)
        logger.info("취소 여부: ${result.isCancelled}")
        logger.info("끝남 여부: ${result.isDone}")
        throw e
    } catch (e: Exception){
        throw e
    }
}


class MyCustomException(message: String) : RuntimeException(message)

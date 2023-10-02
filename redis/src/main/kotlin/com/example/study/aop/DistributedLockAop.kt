package com.example.study.aop

import com.example.study.log.logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RedissonClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Aspect
@Component
class DistributedLockAop @Autowired constructor(
    private val redissonClient: RedissonClient,
    private val aopForTransaction: AopForTransaction
) {
    @Around("@annotation(com.example.study.aop.DistributedLock)")
    fun lock(joinPoint: ProceedingJoinPoint): Any? {
        val signature = joinPoint.signature as MethodSignature
        val method: Method = signature.method
        val distributedLock: DistributedLock = method.getAnnotation(DistributedLock::class.java)
        val key = REDISSON_LOCK_PREFIX + CustomSpringELParser.getDynamicValue(
            signature.parameterNames,
            joinPoint.args,
            distributedLock.key
        )
        val rLock = redissonClient.getLock(key) // (1)
        return try {
            val available =
                rLock.tryLock(distributedLock.waitTime, distributedLock.leaseTime, distributedLock.timeUnit) // (2)
            if (!available) {
                false
            } else aopForTransaction.proceed(joinPoint)
            // (3)
        } catch (e: InterruptedException) {
            throw InterruptedException()
        } finally {
            try {
                rLock.unlock() // (4)
            } catch (e: IllegalMonitorStateException) {
                logger.info(
                    "Redisson Lock Already UnLock {} {}",
                    method.name, key
                )
            }
        }
    }

    companion object {
        private const val REDISSON_LOCK_PREFIX = "LOCK:"
    }
}


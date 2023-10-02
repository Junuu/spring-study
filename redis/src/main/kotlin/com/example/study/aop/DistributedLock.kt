package com.example.study.aop

import java.util.concurrent.TimeUnit


/**
 * Redisson Distributed Lock annotation
 */
@Target(AnnotationTarget.FUNCTION) //어노테이션이 적용될 수 있는 대상
@Retention(AnnotationRetention.RUNTIME) //어노테이션이 유지되는 기간
annotation class DistributedLock(
    /**
     * 락의 이름
     */
    val key: String,
    /**
     * 락의 시간 단위
     */
    val timeUnit: TimeUnit = TimeUnit.SECONDS,
    /**
     * 락을 기다리는 시간 (default - 5s)
     * 락 획득을 위해 waitTime 만큼 대기한다
     */
    val waitTime: Long = 5L,
    /**
     * 락 임대 시간 (default - 3s)
     * 락을 획득한 이후 leaseTime 이 지나면 락을 해제한다
     */
    val leaseTime: Long = 3L
)



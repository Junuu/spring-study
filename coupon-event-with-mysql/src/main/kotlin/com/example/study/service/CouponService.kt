package com.example.study.service

import com.example.study.coupon.EventCouponJpaEntity
import com.example.study.coupon.EventCouponJpaRepository
import com.example.study.coupon.UserCouponJpaEntity
import com.example.study.coupon.UserCouponJpaRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException

@Service
@Transactional
class CouponService(
    private val eventCouponJpaRepository: EventCouponJpaRepository,
    private val userCouponJpaRepository: UserCouponJpaRepository,
) {
    @PostConstruct
    fun dbSetUp(){
        eventCouponJpaRepository.save(
            EventCouponJpaEntity(
                couponName = "test",
                eventCouponCount = 1_000,
            )
        )
    }

    fun getEventCouponRemainBy(name: String): EventCouponJpaEntity{
        return eventCouponJpaRepository.findById(name).orElseThrow()
    }

    fun issueCouponTo(userId: String, couponName: String): String {
        val eventCouponJpaEntity: EventCouponJpaEntity = eventCouponJpaRepository.findById(couponName).orElseThrow()
        if(eventCouponJpaEntity.eventCouponCount == 0L){
            throw IllegalStateException("쿠폰이 모두 소진되었습니다.")
        }
        eventCouponJpaEntity.eventCouponCount = eventCouponJpaEntity.eventCouponCount.dec()
        eventCouponJpaRepository.save(eventCouponJpaEntity)
        userCouponJpaRepository.save(
            UserCouponJpaEntity(
                userId = userId,
                couponName = couponName,
            )
        )
        return couponName
    }
}

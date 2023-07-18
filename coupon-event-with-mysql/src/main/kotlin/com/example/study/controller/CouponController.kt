package com.example.study.controller

import com.example.study.coupon.EventCouponJpaEntity
import com.example.study.coupon.EventCouponJpaRepository
import com.example.study.coupon.UserCouponJpaEntity
import com.example.study.coupon.UserCouponJpaRepository
import com.example.study.service.CouponService
import jakarta.annotation.PostConstruct
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalStateException
import java.util.*

@RestController
class CouponController(
    private val eventCouponJpaRepository: EventCouponJpaRepository,
    private val userCouponJpaRepository: UserCouponJpaRepository,
    private val couponService: CouponService,
) {

    @GetMapping("/event-coupon/{coupon-name}")
    fun showEventCouponRemainingCount(
        @PathVariable(name = "coupon-name") couponName: String,
    ): ResponseEntity<EventCouponJpaEntity> {
        val result = couponService.getEventCouponRemainBy(couponName)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/user/{user-id}/coupon")
    fun issueCouponForUser(
        @PathVariable(name = "user-id") userId: String,
        @RequestBody issueCouponRequest: IssueCouponRequest,
    ): ResponseEntity<String>{
        val result = couponService.issueCouponTo(userId, issueCouponRequest.couponName)
        return ResponseEntity.ok(result)
    }

    @ExceptionHandler
    fun eventEndException(e: IllegalStateException): ResponseEntity<Unit>{
        return ResponseEntity.notFound().build()
    }

    data class IssueCouponRequest(
        val couponName: String,
    )
}

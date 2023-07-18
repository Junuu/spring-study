package com.example.study.coupon

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class EventCouponJpaEntity (
    @Id
    val couponName: String,

    @Column
    var eventCouponCount: Long,
)

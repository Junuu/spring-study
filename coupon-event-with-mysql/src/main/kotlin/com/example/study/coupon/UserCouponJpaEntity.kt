package com.example.study.coupon

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class UserCouponJpaEntity(
    @Id
    val userId: String,

    @Column
    val couponName: String,
)

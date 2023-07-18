package com.example.study.coupon

import org.springframework.data.jpa.repository.JpaRepository

interface UserCouponJpaRepository: JpaRepository<UserCouponJpaEntity, String> {
}

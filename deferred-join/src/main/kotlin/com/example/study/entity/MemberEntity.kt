package com.example.study.entity

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "member")
class MemberEntity (

    @Id
    @Column(nullable = false, name = "member_id")
    val memberId: String = UUID.randomUUID().toString(),

    @Column
    val birthDay: Instant = Instant.now(),

    @Column
    val name: String,
)

package com.example.study.envers.repository

import jakarta.persistence.*
import org.hibernate.envers.Audited
import java.util.*

@Entity
@Table(name = "member")
@Audited
open class UserJpaEntity(
    @Id
    @Column(nullable = false, name = "userId")
    open val userId: String = UUID.randomUUID().toString(),

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    open var board: BoardJpaEntity? = null,

)

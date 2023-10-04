package com.example.study.envers.repository

import jakarta.persistence.*
import org.apache.catalina.User
import org.hibernate.envers.Audited
import org.hibernate.envers.NotAudited
import java.util.UUID

@Entity
@Table(name = "board")
@Audited
open class BoardJpaEntity(
    @Id
    @Column(nullable = false, name = "boardId")
    open val boardId: String = UUID.randomUUID().toString(),


    @Column(nullable = false)
    open var content: String,

    @Column(nullable = true)
    open var addColumn: String? = null,

    @OneToMany(mappedBy = "board", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @NotAudited
    open val comments: MutableList<CommentJpaEntity> = mutableListOf(),

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = true)
    @NotAudited
    open var user: UserJpaEntity? = null,
): BaseEntity()

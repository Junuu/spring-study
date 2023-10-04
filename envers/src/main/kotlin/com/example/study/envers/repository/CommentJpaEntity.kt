package com.example.study.envers.repository

import jakarta.persistence.*
import org.hibernate.envers.AuditMappedBy
import org.hibernate.envers.Audited
import java.util.*

@Entity
@Audited
@Table(name = "comment")
open class CommentJpaEntity(
    @Id
    @Column(nullable = false)
    open val commentId: String = UUID.randomUUID().toString(),

    @Column(nullable = false)
    open var comment: String,

    @Column(nullable = false)
    open var boardId: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId", referencedColumnName = "boardId", insertable = false, updatable = false)
    open var board: BoardJpaEntity? = null,
)



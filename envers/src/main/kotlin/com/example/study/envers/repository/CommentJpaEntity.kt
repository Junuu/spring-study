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
    val commentId: String = UUID.randomUUID().toString(),

    @Column(nullable = false)
    var comment: String,

    @Column(nullable = false)
    var boardId: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId", referencedColumnName = "boardId", insertable = false, updatable = false)
    var board: BoardJpaEntity? = null,
)



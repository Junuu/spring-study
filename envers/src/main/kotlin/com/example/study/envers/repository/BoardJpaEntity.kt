package com.example.study.envers.repository

import jakarta.persistence.*
import org.hibernate.envers.Audited
import java.util.UUID

@Entity
@Table(name = "board")
@Audited
open class BoardJpaEntity(
    @Id
    @Column(nullable = false, name = "boardId")
    val boardId: String = UUID.randomUUID().toString(),

    @Column(nullable = false)
    var content: String,

    @Column(nullable = true)
    var addColumn: String? = null,

    @OneToMany(mappedBy = "board", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val comments: MutableList<CommentJpaEntity> = mutableListOf(),
): BaseEntity()

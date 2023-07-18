package com.example.study.envers.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.history.RevisionRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardJpaRepository :
    JpaRepository<BoardJpaEntity, String>,
    RevisionRepository<BoardJpaEntity, String, Long> {

    @Modifying
    @Query("update BoardJpaEntity u set u.addColumn = :someValue where u.boardId = :boardId")
    fun updateAddColumn(someValue: String, boardId: String): Int
}

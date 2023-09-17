package com.example.study.repository

import com.example.study.entity.TestEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Window
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TestEntityRepository: JpaRepository<TestEntity, UUID> {
    fun findPageBy(pageable: Pageable): Page<TestEntity>
    fun findSliceBy(pageable: Pageable): Slice<TestEntity>

    fun findWindowBy(pageable: Pageable): Window<TestEntity>
}

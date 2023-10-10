package com.example.study.repository

import com.example.study.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MemberJpaRepository: JpaRepository<MemberEntity, UUID> {
}

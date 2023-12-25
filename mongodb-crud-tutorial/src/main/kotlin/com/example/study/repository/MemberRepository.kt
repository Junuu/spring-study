package com.example.study.repository

import com.example.study.domain.Member
import org.springframework.data.mongodb.repository.MongoRepository

interface MemberRepository: MongoRepository<Member, String> {
}
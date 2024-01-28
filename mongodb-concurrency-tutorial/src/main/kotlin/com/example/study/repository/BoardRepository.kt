package com.example.study.repository

import com.example.study.domain.Board
import org.springframework.data.mongodb.repository.MongoRepository

interface BoardRepository: MongoRepository<Board, String> {
}
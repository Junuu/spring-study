package com.example.study.service

import com.example.study.domain.Board
import com.example.study.log.logger
import com.example.study.repository.BoardRepository
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
        private val boardRepository: BoardRepository,
        private val mongoTemplate: MongoTemplate,
){
    fun findPostBy(id: String): Board {
        return boardRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("${id}로 게시글을 조회할 수 없습니다")
    }

    @Transactional
    fun registerPost(): String{
        return boardRepository.save(Board()).id!!
    }

    @Transactional
    fun increaseViewCount(id: String): Board{
        val board = boardRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("${id}로 게시글을 조회할 수 없습니다")
        logger.info { "조회수 증가가 호출되었습니다"}
        board.addHits()
        return boardRepository.save(board)
    }

    @Transactional
    fun increaseViewCountV2(id: String): Board{
        val query = Query(Criteria.where("_id").`is`(id))
        val update = Update().inc("hit", 1)
        val options = FindAndModifyOptions().returnNew(true)
        return mongoTemplate.findAndModify(query, update, options, Board::class.java)
                ?: throw IllegalArgumentException("${id}로 게시글을 조회할 수 없습니다")
    }

    @Transactional

    fun deletePost(id: String){
        boardRepository.deleteById(id)
    }

}
package com.example.study.envers.controller

import com.example.study.envers.repository.BoardJpaEntity
import com.example.study.envers.repository.BoardJpaRepository
import com.example.study.envers.repository.CommentJpaEntity
import com.example.study.envers.repository.UserJpaEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class BoardAuditCheckService(
    private val boardJpaRepository: BoardJpaRepository,
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun createTest(): String{
        //INSERT
        val boardId = UUID.randomUUID().toString()
        val board = BoardJpaEntity(boardId = boardId, content = "content", comments = mutableListOf(
            CommentJpaEntity(comment = "comment", boardId = boardId),
        ),
            user = UserJpaEntity(),
        )

        val savedBoardJpaEntity = boardJpaRepository.saveAndFlush(board)



        return savedBoardJpaEntity.boardId
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun updateTest(boardId: String){
        //UPDATE
        val board = boardJpaRepository.findById(boardId).orElseThrow()
        board.content = "update_content"
        board.comments.first().comment = "update_comment"
        boardJpaRepository.saveAndFlush(board)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deleteTest(boardId: String){
        //DELETE
        val board = boardJpaRepository.findById(boardId).orElseThrow()
        boardJpaRepository.delete(board)
    }

}

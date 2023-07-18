package com.example.study.envers.controller

import com.example.study.envers.repository.BoardJpaRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class BoardAuditCheckController(
    private val boardAuditCheckService: BoardAuditCheckService,
    private val boardJpaRepository: BoardJpaRepository,
) {

    @GetMapping("/audit")
    fun boardAuditView(){
        val boardId = boardAuditCheckService.createTest()
        boardAuditCheckService.updateTest(boardId)
        boardAuditCheckService.deleteTest(boardId)
    }

    @GetMapping("/update-with-jqpl")
    @Transactional
    fun boardUpdateWithJPQL(
        @RequestParam boardId: String,
    ){
        val board = boardJpaRepository.findById(boardId).orElseThrow()
        boardJpaRepository.updateAddColumn("someValue", boardId)
        boardJpaRepository.save(board)
    }

    @GetMapping("/update-with-dirty-check")
    @Transactional
    fun boardUpdateWithDirtyChecking(
        @RequestParam boardId: String,
    ){
        val board = boardJpaRepository.findById(boardId).orElseThrow()
        board.addColumn = "dirty-check-update"
        boardJpaRepository.save(board)
    }
}

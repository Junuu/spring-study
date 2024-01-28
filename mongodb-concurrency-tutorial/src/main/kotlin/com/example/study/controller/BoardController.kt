package com.example.study.controller

import com.example.study.domain.Board
import com.example.study.service.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/boards")
class BoardController(
        private val boardService: BoardService,
) {

    @GetMapping("/{id}")
    fun findPostBy(@PathVariable id: String): ResponseEntity<Board> {
        val board = boardService.findPostBy(id)
        return ResponseEntity.ok(board)
    }

    @PostMapping
    fun registerPost(): ResponseEntity<String> {
        val id = boardService.registerPost()
        return ResponseEntity.ok(id)
    }

    @PutMapping("/{id}/hit")
    fun increaseViewCountV2(
            @PathVariable id: String,
    ): ResponseEntity<Board> {
        val board = boardService.increaseViewCountV2(id = id)
        return ResponseEntity.ok(board)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: String): ResponseEntity<Unit> {
        boardService.deletePost(id = id)
        return ResponseEntity.noContent().build()
    }
}
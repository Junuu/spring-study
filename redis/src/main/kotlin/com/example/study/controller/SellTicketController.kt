package com.example.study.controller

import com.example.study.log.logger
import com.example.study.service.SellTicketService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SellTicketController(
    private val sellTicketService: SellTicketService,
) {

    @PostMapping("/ticket/{id}")
    fun sellTicket(@PathVariable id: Long): ResponseEntity<Unit> {
        sellTicketService.sellTicket(id)
        return ResponseEntity.ok().build()
    }
}

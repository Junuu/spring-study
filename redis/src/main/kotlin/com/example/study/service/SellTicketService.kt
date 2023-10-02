package com.example.study.service

import com.example.study.aop.DistributedLock
import com.example.study.entity.TicketRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SellTicketService(
    private val ticketRepository: TicketRepository,
) {

    @DistributedLock(key = "#id")
    fun sellTicket(id: Long) {
        val ticket = ticketRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("해당 id로 티켓을 조회할 수 없습니다 id = $id")
        ticket.sell()
        ticketRepository.save(ticket)
    }
}

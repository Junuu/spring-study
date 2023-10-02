package com.example.study

import com.example.study.entity.Ticket
import com.example.study.entity.TicketRepository
import com.example.study.service.SellTicketService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors.*


@SpringBootTest
class ApplicationTests @Autowired constructor(
	private val sellTicketService: SellTicketService,
	private val ticketRepository: TicketRepository,
) {

	@Test
	fun `100개의 티켓을 만들고 50개의 요청을 동시에 요청하면 50개의 티켓수량이 남아있어야 한다`(){
		val demoTicket = Ticket(
			name = "demo",
			availableStock = 100L,
		)
		val ticket = ticketRepository.save(demoTicket)
		val numberOfThreads = 50
		val id = ticket.id!!

		val executorService = newFixedThreadPool(50)

		val latch = CountDownLatch(numberOfThreads)

		repeat(numberOfThreads) {
			executorService.submit {
				try {
					sellTicketService.sellTicket(id)
				} finally {
					latch.countDown()
				}
			}
		}
		latch.await()

		val actual= ticketRepository.findById(id)
			.orElseThrow { IllegalArgumentException() }

		Assertions.assertEquals(50, actual.getAvailableStockCount())
	}

}

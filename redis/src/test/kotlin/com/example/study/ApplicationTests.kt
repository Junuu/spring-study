package com.example.study

import com.example.study.adapter.RedisTransactionRollbackTest
import com.example.study.adapter.RedisTransactionWithLuaScript
import com.example.study.adapter.RedisTransactionWithLuaScriptRollback
import com.example.study.entity.Ticket
import com.example.study.entity.TicketRepository
import com.example.study.service.DecreaseStockService
import com.example.study.service.SellTicketService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors.*


@SpringBootTest
class ApplicationTests @Autowired constructor(
	private val sellTicketService: SellTicketService,
	private val ticketRepository: TicketRepository,
	private val decreaseStockService: DecreaseStockService,
	private val stringRedisTemplate: StringRedisTemplate,
	private val redisTransactionRollbackTest: RedisTransactionRollbackTest,
	private val redisTransactionWithLuaScript: RedisTransactionWithLuaScript,
	private val redisTransactionWithLuaScriptRollback: RedisTransactionWithLuaScriptRollback,
) {

	@Test
	fun `luaScript 명령 중간에 예외가 발생하면 나머지 명령들은 어떻게 될까?`(){
		redisTransactionWithLuaScriptRollback.internalException()
	}

	@Test
	fun `luaScript 에러 메시지 확인을 위해 재고0으로 세팅하고 단건 호출`(){
		redisTransactionWithLuaScript.luaScriptDeceaseStock()
		//Caused by: io.lettuce.core.RedisCommandExecutionException:
		// ERR user_script:6: Inventory cannot be less than 0.
		//Please replenish inventory script: 128214c0c509947d73c0c678c4eeb89b40f67309, on @user_script:6.

		//runCatching 으로 감싸서 새로운 예외를 던지면 com.example.study.adapter.StockEmptyException: 재고가 소진되었습니다.
	}

	@Test
	fun `luaScript를 활용하여 Atomic 연산을 수행하여 30개의 재고가 존재할 때 50개의 요청을 동시에 보내면 0개의 재고가 남아있어야 한다`(){
		val numberOfThreads = 50
		val executorService = newFixedThreadPool(numberOfThreads)
		val latch = CountDownLatch(numberOfThreads)

		repeat(numberOfThreads) {
			executorService.submit {
				try {
					redisTransactionWithLuaScript.luaScriptDeceaseStock()
				} finally {
					latch.countDown()
				}
			}
		}
		latch.await()

		val actual = stringRedisTemplate.opsForHash<String, String>().get("productItem", "104")
		assertEquals(actual, "0")
	}

	@Test
	fun `트랜잭션 내부에서 watch 명령어의 충돌으로 실패하는 경우 명령어들은 어떻게 수행될까?`(){
		val numberOfThreads = 50
		val executorService = newFixedThreadPool(numberOfThreads)
		val latch = CountDownLatch(numberOfThreads)

		repeat(numberOfThreads) {
			executorService.submit {
				try {
					redisTransactionRollbackTest.rollbackTest()
				} finally {
					latch.countDown()
				}
			}
		}
		latch.await()
	}

	@Test
	fun `트랜잭션을 내부에서 명령어1을 수행하고 예외가 발생하고 명령어2가 수행되면 어떻게 될까?`(){
		redisTransactionRollbackTest.rollbackTest()
	}

	@Test
	fun `30개의 재고가 존재할 때 50개의 요청을 동시에 보내면 0개의 재고가 남아있어야 한다`(){
		val numberOfThreads = 50
		val executorService = newFixedThreadPool(numberOfThreads)
		val latch = CountDownLatch(numberOfThreads)

		repeat(numberOfThreads) {
			executorService.submit {
				try {
					decreaseStockService.decrease()
				} finally {
					latch.countDown()
				}
			}
		}
		latch.await()

		val actual = stringRedisTemplate.opsForHash<String, String>().get("productItem", "104")
		assertEquals(actual, "0")
	}

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

		assertEquals(50, actual.getAvailableStockCount())
	}

}

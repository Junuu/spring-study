package com.example.study.batch.job.database

import com.example.study.application.jpa.TestJpaEntity
import com.example.study.application.jpa.TestJpaEntityRepository
import com.example.study.application.jpa.TestStatus
import com.example.study.log.logger
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JdbcCursorItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Profile("database-job")
@Configuration
class DataBaseJobConfig(
    private val jobRepository: JobRepository,
    private val batchTransactionManager: PlatformTransactionManager,
    private val testJpaEntityRepository: TestJpaEntityRepository,
) {

    @Bean
    fun databaseJob(
        chunkStep: Step
    ): Job {
        return JobBuilder("database job", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(chunkStep)
            .build()
    }

    @Bean
    fun chunkStep(
        reader: ItemReader<out ReaderDto>,
    ): Step {
        return StepBuilder("database step", jobRepository)
            .chunk<ReaderDto, ReaderDto>(BATCH_SIZE, batchTransactionManager)
            .reader(reader)
            .writer(updateStatusWaitingToReadyItemWriter())
            .build()
    }

    @StepScope
    @Bean
    fun findTestJpaEntityReader(
        dataSource: DataSource,
        @Value("#{jobParameters['current.dates']}") currentDate: String,
    ): JdbcCursorItemReader<ReaderDto> {
        logger.info { "currentDate: $currentDate" }

        return JdbcCursorItemReaderBuilder<ReaderDto>()
            .name("testDBReader")
            .dataSource(dataSource)
            .sql(findTestSQL)
            .rowMapper(DataClassRowMapper(ReaderDto::class.java))
            .build()
    }

    @StepScope
    @Bean
    fun updateStatusWaitingToReadyItemWriter(): ItemWriter<ReaderDto> {
        return ItemWriter { items ->
            items.run {
                logger.info("Writing item start")
                items.forEach {
                    logger.info("Writing item : {}", it)
                    testJpaEntityRepository.save(TestJpaEntity(id= it.id, status = TestStatus.READY))
                }
            }
        }
    }

    companion object{
        val findTestSQL = """
            SELECT * FROM test_table
        """.trimIndent()

        const val BATCH_SIZE = 5
    }
}

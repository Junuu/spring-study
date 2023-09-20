package com.example.study.batch.job.test

import com.example.study.application.jpa.TestJpaEntity
import com.example.study.application.jpa.TestJpaEntityRepository
import com.example.study.log.logger
import jakarta.annotation.PostConstruct
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.support.ListItemReader
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.transaction.PlatformTransactionManager


@Profile("test-job")
@Configuration
class TestJobConfig @Autowired constructor(
    private val jobRepository: JobRepository,
    private val batchTransactionManager: PlatformTransactionManager,
    private val testJpaEntityRepository: TestJpaEntityRepository,
) {

    @PostConstruct
    fun initDatabase(){
        repeat(10){
            testJpaEntityRepository.save(TestJpaEntity())
        }
    }

    @Bean
    fun firstJob(): Job {
        return JobBuilder("first job", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(chunkStep())
            .next(taskletStep())
            .build()
    }

    @Bean
    fun taskletStep(): Step {
        logger.info("TaskletStep is start")
        return StepBuilder("first step", jobRepository)
            .tasklet({ _: StepContribution, chunkContext: ChunkContext ->
                logger.info("This is first tasklet step")
                logger.info("SEC = {}", chunkContext.stepContext.stepExecutionContext)
                RepeatStatus.FINISHED
            }, batchTransactionManager).build()
    }

    @Bean
    fun chunkStep(): Step {
        return StepBuilder("first step", jobRepository)
            .chunk<String, String>(BATCH_SIZE, batchTransactionManager)
            .reader(beanReader())
            .writer(ClassItemWriter())
            .build()
    }

    @Bean
    fun beanReader(): ItemReader<String> {
        val data: List<String> = mutableListOf(
            "Byte",
            "Code",
            "Data",
            "Disk",
            "File",
            "Input",
            "Loop",
            "Logic",
            "Mode",
            "Node",
            "Port",
            "Query",
            "Ratio",
            "Root",
            "Route",
            "Scope",
            "Syntax",
            "Token",
            "Trace"
        )
        logger.info ("Reading item: {}", data)
        return ListItemReader(data)
    }

    class ClassItemWriter: ItemWriter<String> {
        override fun write(itmes: Chunk<out String>) {
            logger.info("Writing item start")
            itmes.forEach {
                logger.info("Writing item : {}", it)
            }
        }
    }

    companion object {
        private const val BATCH_SIZE = 5
    }
}

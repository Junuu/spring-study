package com.example.study.batch.job.skip

import com.example.study.batch.job.CustomSkipPolicy
import com.example.study.log.logger
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.support.ListItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.transaction.PlatformTransactionManager
import java.lang.IllegalArgumentException

@Profile("skip-job")
@Configuration
class SkipJobConfig(
    private val jobRepository: JobRepository,
    private val batchTransactionManager: PlatformTransactionManager,
) {
    @Bean
    fun skipJob(): Job {
        return JobBuilder("skipJob job", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(chunkStep())
            .build()
    }

    @Bean
    fun chunkStep(): Step {
        return StepBuilder("skipJob step", jobRepository)
            .chunk<String, String>(BATCH_SIZE, batchTransactionManager)
            .reader(beanReader())
            .writer(ClassItemWriter())
            .faultTolerant()
//            .skip(IllegalArgumentException::class.java)
//            .skipLimit(20)
            .skipPolicy(CustomSkipPolicy())
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
                if(it.contains('o')){
                    logger.warn("o가 포함되어 IllegalArgumentException 발생 {}", it)
                    throw IllegalArgumentException("문자에 o가 들어가는 경우 예외를 만들면 어떻게 될까?")
                }
            }

        }
    }

    companion object {
        private const val BATCH_SIZE = 5
    }
}

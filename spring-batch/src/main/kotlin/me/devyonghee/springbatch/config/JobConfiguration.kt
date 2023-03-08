package me.devyonghee.springbatch.config

import me.devyonghee.springbatch.application.ItemFailureLoggerListener
import me.devyonghee.springbatch.application.StepProcessor
import me.devyonghee.springbatch.application.StepReader
import me.devyonghee.springbatch.application.StepWriter
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.listener.CompositeJobExecutionListener
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource


@Configuration
class JobConfiguration(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager
) {

    @Bean
    fun job(): Job {
        return JobBuilder("simpleJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .listener(object : CompositeJobExecutionListener() {
                override fun afterJob(jobExecution: JobExecution) {
                    println("afterJob")
                }

                override fun beforeJob(jobExecution: JobExecution) {
                    println("beforeJob")
                }
            })
            .start(simpleStep1())
            .build()
    }

    @Bean
    fun simpleStep1(): Step {
        return StepBuilder("simpleStep1", jobRepository)
            .chunk<String, String>(1, transactionManager)
            .reader(StepReader())
            .processor(StepProcessor())
            .writer(StepWriter())
            .exceptionHandler(ItemFailureLoggerListener())
            .build();
    }


}
package me.devyonghee.springbatch.config

import me.devyonghee.springbatch.application.SimpleTasklet
import me.devyonghee.springbatch.application.StepLogExceptionHandler
import org.springframework.batch.core.*
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.listener.CompositeJobExecutionListener
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class JobConfiguration(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager
) : DefaultBatchConfiguration() {

    @Bean
    fun job(simpleStep: Step): Job {
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
            .start(simpleStep)
            .build()
    }

    @Bean
    fun simpleStep1(simple: SimpleTasklet, stepLogExceptionHandler: StepLogExceptionHandler): Step {
        return StepBuilder("simpleStep1", jobRepository)
            .chunk<String, String>(5, transactionManager)
            .reader(simple)
            .processor(simple)
            .writer(simple)
            .listener(object : StepExecutionListener {
                override fun afterStep(stepExecution: StepExecution): ExitStatus {
                    print("afterStep")
                    if (stepExecution.readCount > 3) {
                        return ExitStatus.COMPLETED
                    }
                    return ExitStatus.EXECUTING
                }
            })
            .exceptionHandler(stepLogExceptionHandler)
            .build();
    }


}
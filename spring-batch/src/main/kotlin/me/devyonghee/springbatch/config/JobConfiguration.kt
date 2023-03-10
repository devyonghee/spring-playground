package me.devyonghee.springbatch.config

import jakarta.persistence.EntityManagerFactory
import me.devyonghee.springbatch.payment.PaymentGroup
import me.devyonghee.springbatch.payment.PaymentStatus
import me.devyonghee.springbatch.payment.Settlement
import me.devyonghee.springbatch.payment.StepLogExceptionHandler
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.batch.MyBatisPagingItemReader
import org.springframework.batch.core.*
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.listener.CompositeJobExecutionListener
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class JobConfiguration(
    private val jobRepository: JobRepository,
) {

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
    fun simpleStep1(
        transactionManager: PlatformTransactionManager,
        reader: ItemReader<PaymentGroup>,
        processor: ItemProcessor<PaymentGroup, Settlement>,
        writer: ItemWriter<Settlement>,
        stepLogExceptionHandler: StepLogExceptionHandler,
    ): Step {
        return StepBuilder("simpleStep1", jobRepository)
            .chunk<PaymentGroup, Settlement>(5, transactionManager)
            .reader(reader)
            .processor(processor)
            .writer(writer)
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

    @Bean
    fun reader(sqlSessionFactory: SqlSessionFactory): ItemReader<PaymentGroup> {
        return MyBatisPagingItemReader<PaymentGroup>().apply {
            setSqlSessionFactory(sqlSessionFactory)
            setQueryId("me.devyonghee.springbatch.payment.PaymentGroupRepository.findPaymentGroupByStatus")
            setParameterValues(mapOf("status" to PaymentStatus.READY))
            setPageSize(2)
        }
    }

    @Bean
    @StepScope
    fun processor(): ItemProcessor<PaymentGroup, Settlement> {
        return ItemProcessor {
            Settlement(it.totalAmount, it.memberId)
        }
    }

    @Bean
    @StepScope
    fun writer(entityManagerFactory: EntityManagerFactory): JpaItemWriter<Settlement> {
        return JpaItemWriterBuilder<Settlement>()
            .entityManagerFactory(entityManagerFactory)
            .usePersist(true)
            .build()
    }
}
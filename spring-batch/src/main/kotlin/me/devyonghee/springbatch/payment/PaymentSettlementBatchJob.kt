package me.devyonghee.springbatch.payment

import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManagerFactory
import me.devyonghee.springbatch.payment.domain.PaymentGroup
import me.devyonghee.springbatch.payment.domain.Settlement
import me.devyonghee.springbatch.payment.domain.StepLogExceptionHandler
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder
import org.quartz.*
import org.springframework.batch.core.*
import org.springframework.batch.core.Job
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.listener.CompositeJobExecutionListener
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


@Configuration
@EnableBatchProcessing
class PaymentSettlementBatchJob(
    private val transactionManager: PlatformTransactionManager,
    private val sqlSessionFactory: SqlSessionFactory,
    private val stepLogExceptionHandler: StepLogExceptionHandler,
    private val entityManagerFactory: EntityManagerFactory,
    private val scheduler: Scheduler,
    private val jobRepository: JobRepository,
) {

    @PostConstruct
    fun exampleJob1Trigger() {
        val job: JobDetail = org.quartz.JobBuilder.newJob(PaymentSettlementQuartzJob::class.java)
            .setJobData(JobDataMap(mapOf("jobName" to "paymentSettlementJob")))
            .build()
        val trigger: Trigger = TriggerBuilder.newTrigger()
//            .withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?"))
            .withSchedule(
                SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(10)
                    .repeatForever()
            )
            .build()
        scheduler.scheduleJob(job, trigger)
    }

    @Bean()
    fun job(): Job {
        return JobBuilder("paymentSettlementJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .listener(object : CompositeJobExecutionListener() {
                override fun afterJob(jobExecution: JobExecution) {
                    println("afterJob")
                }

                override fun beforeJob(jobExecution: JobExecution) {
                    println("beforeJob")
                }
            })
            .start(simpleStep())
            .build()
    }

    @Bean
    fun simpleStep(): Step {
        return StepBuilder("simpleStep", jobRepository)
            .chunk<PaymentGroup, Settlement>(5, transactionManager)
            .reader(reader())
            .processor(processor())
            .writer(writer())
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
    fun reader(): ItemReader<PaymentGroup> {
        val today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
        return MyBatisPagingItemReaderBuilder<PaymentGroup>()
            .sqlSessionFactory(sqlSessionFactory)
            .parameterValues(
                mapOf(
                    "fromDateTime" to today,
                    "toDateTime" to today.plusDays(1)
                )
            )
            .queryId("me.devyonghee.springbatch.payment.domain.PaymentGroupRepository.findPaymentGroupByStatus")
            .pageSize(3)
            .build()
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
    fun writer(): JpaItemWriter<Settlement> {
        return JpaItemWriterBuilder<Settlement>()
            .entityManagerFactory(entityManagerFactory)
            .usePersist(true)
            .build()
    }

}
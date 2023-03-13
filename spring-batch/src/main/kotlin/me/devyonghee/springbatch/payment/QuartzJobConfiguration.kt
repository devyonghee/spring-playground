package me.devyonghee.springbatch.payment

import org.quartz.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.configuration.JobLocator
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.QuartzJobBean
import java.time.LocalDateTime


@Configuration
@DisallowConcurrentExecution
class PaymentQuartzJobConfiguration(
    private val jobLauncher: JobLauncher,
    private val jobLocator: JobLocator,
) : QuartzJobBean() {

    override fun executeInternal(context: JobExecutionContext) {
        val job: Job = jobLocator.getJob("paymentSettlementJob")
        log.info("[{}] started.", job.name)
        val params: JobParameters = JobParametersBuilder()
            .addString("JobId", System.currentTimeMillis().toString())
            .addString(INSTANCE_ID_KEY, context.scheduler.schedulerInstanceId)
            .addLocalDateTime(DATE_TIME_STAMP_KEY, LocalDateTime.now())
            .toJobParameters()
        try {
            jobLauncher.run(job, params)
            log.info("[{}] completed.", job.name)
        } catch (e: Exception) {
            log.error("paymentSettlement batch job failed for params('$params')", e)
            log.info("[{}] completed.", job.name)

        }
    }


    companion object {
        private val log: Logger = LoggerFactory.getLogger(PaymentQuartzJobConfiguration::class.java)
        private const val INSTANCE_ID_KEY = "dateTimeStamp"
        private const val DATE_TIME_STAMP_KEY = "dateTimeStamp"
    }
}
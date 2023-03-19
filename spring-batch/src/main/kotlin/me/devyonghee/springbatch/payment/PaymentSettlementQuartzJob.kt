package me.devyonghee.springbatch.payment

import org.quartz.DisallowConcurrentExecution
import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.configuration.JobLocator
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.quartz.QuartzJobBean
import java.time.LocalDate


@DisallowConcurrentExecution
@Suppress("MemberVisibilityCanBePrivate")
class PaymentSettlementQuartzJob : QuartzJobBean() {

    var jobName: String? = null
    var jobLauncher: JobLauncher? = null
    var jobLocator: JobLocator? = null

    override fun executeInternal(context: JobExecutionContext) {
        val job: Job = jobLocator!!.getJob(jobName)
        log.info("{} started", jobName)
        val params: JobParameters = JobParametersBuilder()
            .addString("JobId", System.currentTimeMillis().toString())
            .addLocalDateTime("targetDatetime", LocalDate.now().atStartOfDay())
            .toJobParameters()
        try {
            jobLauncher!!.run(job, params)
            log.info("{} completed", jobName)
        } catch (e: Exception) {
            log.error("paymentSettlement batch job failed for params('$params')", e)
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(PaymentSettlementQuartzJob::class.java)
    }
}
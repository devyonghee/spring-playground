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
import org.springframework.stereotype.Component


@Component
@DisallowConcurrentExecution
class PaymentSettlementQuartzJob(
    private val jobLauncher: JobLauncher,
    private val jobLocator: JobLocator,
) : QuartzJobBean() {

    override fun executeInternal(context: JobExecutionContext) {
        val job: Job = jobLocator.getJob(context.jobDetail.jobDataMap.getString("jobName"))
        log.info("{} started.", job.name)
        val params: JobParameters = JobParametersBuilder()
            .addString("JobId", System.currentTimeMillis().toString())
            .toJobParameters()
        try {
            jobLauncher.run(job, params)
            log.info("{} completed.", job.name)
        } catch (e: Exception) {
            log.error("paymentSettlement batch job failed for params('$params')", e)
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(PaymentSettlementQuartzJob::class.java)
    }
}
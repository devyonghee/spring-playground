package me.devyonghee.springbatch.payment

import org.quartz.*
import org.springframework.batch.core.configuration.JobLocator
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component


@Component
class PaymentSettlementQuartzConfiguration(
    private val jobLocator: JobLocator,
    private val jobLauncher: JobLauncher,
    private val scheduler: Scheduler,
) : InitializingBean {

    override fun afterPropertiesSet() {
        scheduler.scheduleJob(jobDetail(), trigger())
    }

    fun trigger(): Trigger {
        return TriggerBuilder.newTrigger()
//            .withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?"))
            .withSchedule(
                SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(10)
                    .repeatForever()
            )
            .build()
    }

    fun jobDetail(): JobDetail {
        val jobDataMap = JobDataMap().apply {
            put("jobName", PaymentSettlementBatchJob.JOB_NAME)
            put("jobLocator", jobLocator)
            put("jobLauncher", jobLauncher)
        }
        return JobBuilder.newJob(PaymentSettlementQuartzJob::class.java)
            .withIdentity(PaymentSettlementBatchJob.JOB_NAME)
            .setJobData(jobDataMap)
            .storeDurably()
            .build()
    }
}
package me.devyonghee.springbatch.payment

import org.quartz.*
import org.springframework.batch.core.configuration.JobLocator
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.SchedulerFactoryBean


@Configuration
class PaymentSettlementQuartzConfiguration(
    private val jobLocator: JobLocator,
    private val jobLauncher: JobLauncher,
) {

    @Bean
    fun schedulerFactoryBean(): SchedulerFactoryBean? {
        val scheduler = SchedulerFactoryBean()
        scheduler.setTriggers(trigger())
        scheduler.setJobDetails(jobDetail())
        return scheduler
    }

    @Bean
    fun trigger(): Trigger? {
        return TriggerBuilder.newTrigger()
//            .withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?"))
            .withSchedule(
                SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(10)
                    .repeatForever()
            )
            .forJob(PaymentSettlementBatchJob.JOB_NAME)
            .build()
    }

    @Bean
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
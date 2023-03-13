package me.devyonghee.springbatch.configuration

import org.springframework.batch.core.configuration.JobRegistry
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableBatchProcessing
class QuartzConfiguration {

    @Bean
    fun jobRegistryBeanPostProcessor(jobRegistry: JobRegistry): JobRegistryBeanPostProcessor {
        val beanPostProcessor = JobRegistryBeanPostProcessor()
        beanPostProcessor.setJobRegistry(jobRegistry)
        return beanPostProcessor
    }
}
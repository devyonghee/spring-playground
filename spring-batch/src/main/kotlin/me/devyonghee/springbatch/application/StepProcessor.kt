package me.devyonghee.springbatch.application

import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
@StepScope
class StepProcessor : ItemProcessor<String, String?> {

    private lateinit var someObject: Any

    override fun process(item: String): String {
        println("$someObject $item")
        return "process"
    }

    @BeforeStep
    fun retrieveInterstepData(stepExecution: StepExecution) {
        val jobExecution = stepExecution.jobExecution
        val jobContext = jobExecution.executionContext
        someObject = jobContext["step2"]
    }
}
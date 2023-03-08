package me.devyonghee.springbatch.application

import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
@StepScope
class StepWriter : ItemWriter<String> {

    private lateinit var stepExecution: StepExecution

    override fun write(items: Chunk<out String>) {
        System.out.println("Items from processor : $items")
        val stepContext: ExecutionContext = stepExecution.executionContext
        stepContext.put("step2", "Pass To Next Step")
    }

    @BeforeStep
    fun saveStepExecution(stepExecution: StepExecution) {
        this.stepExecution = stepExecution
    }
}
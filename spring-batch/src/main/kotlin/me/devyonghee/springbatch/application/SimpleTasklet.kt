package me.devyonghee.springbatch.application

import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component


@Component
@StepScope
class SimpleTasklet(
//    @Value("#{jobParameters[requestDate]}") private val requestDate: String
) : ItemReader<String>, ItemProcessor<String, String>, ItemWriter<String> {

    override fun read(): String {
        println("read items")
        return "read item"
    }

    override fun process(item: String): String {
        println("process $item")
        return item
    }

    override fun write(items: Chunk<out String>) {
        println("item write : $items")
    }
}
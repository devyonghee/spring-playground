package me.devyonghee.springbatch.application

import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component

@Component
@StepScope
class StepReader : ItemReader<String> {

    override fun read(): String {
        System.out.println("read items")
        return "read items"
    }
}
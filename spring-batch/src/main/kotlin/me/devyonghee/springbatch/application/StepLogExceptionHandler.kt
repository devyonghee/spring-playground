package me.devyonghee.springbatch.application

import org.slf4j.LoggerFactory
import org.springframework.batch.repeat.RepeatContext
import org.springframework.batch.repeat.exception.ExceptionHandler
import org.springframework.stereotype.Component

@Component
class StepLogExceptionHandler : ExceptionHandler {

    override fun handleException(context: RepeatContext, throwable: Throwable) {
        logger.error("Item failure", throwable)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(StepLogExceptionHandler::class.java)
    }
}
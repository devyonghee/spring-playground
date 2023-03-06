package me.devyonghee.flywayorm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlywayOrmApplication

fun main(args: Array<String>) {
    runApplication<FlywayOrmApplication>(*args)
}

package me.devyonghee.flywayorm.config

import org.jetbrains.exposed.sql.DatabaseConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfiguration {

    @Bean
    fun exposedDatabaseConfig() = DatabaseConfig {
        useNestedTransactions = true
    }
}
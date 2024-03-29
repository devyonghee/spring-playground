package me.devyonghee.flywayorm.config

import org.jetbrains.exposed.spring.SpringTransactionManager
import org.jetbrains.exposed.sql.DatabaseConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
class ExposedConfiguration {

    @Bean
    fun exposedDatabaseConfig() = DatabaseConfig {
        useNestedTransactions = true
    }

    @Bean
    fun transactionManager(dataSource: DataSource): SpringTransactionManager {
        return SpringTransactionManager(dataSource)
    }
}
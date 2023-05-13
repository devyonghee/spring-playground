package me.devyonghee.mysqlcdc.config

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DebeziumConfiguration {

    @Bean
    fun customerConnector(datasource: DataSourceProperties): io.debezium.config.Configuration {
        return io.debezium.config.Configuration.create()
            .with("name", "customer-mysql-connector")
            .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
            .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
            .with("offset.storage.file.filename", "/tmp/offsets.dat")
            .with("offset.flush.interval.ms", "60000")
            .with("database.hostname", "localhost")
            .with("database.port", "3306")
            .with("database.user", datasource.username)
            .with("database.password", datasource.password)
            .with("database.dbname", datasource.driverClassName)
            .with("include.schema.changes", "false")
            .with("database.server.id", "10181")
            .with("database.server.name", "customer-mysql-db-server")
            .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
            .with("database.history.file.filename", "/tmp/dbhistory.dat")
            .build()
    }
}
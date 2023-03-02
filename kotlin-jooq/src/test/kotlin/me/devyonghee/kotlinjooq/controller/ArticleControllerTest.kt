package me.devyonghee.kotlinjooq.controller

import org.flywaydb.core.Flyway
import org.jooq.tools.jdbc.SingleConnectionDataSource
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.sql.DriverManager

class ArticleControllerTest {

    @Test
    fun setUp() {
        DriverManager.getConnection("jdbc:h2:mem:jooqdb", "sa", "").use { c ->
            val flyway: Flyway = Flyway.configure().dataSource(SingleConnectionDataSource(c)).load()
            flyway.migrate()
        }
    }
}
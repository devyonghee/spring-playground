package me.devyonghee.flywayorm.article.persistence.exposed.model

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ArticleTable : IdTable<String>("article") {

    override val id: Column<EntityID<String>> = varchar("slug", 255).entityId()
    val title: Column<String> = varchar("title", 255)
    val description: Column<String> = varchar("description", 255)
    val body: Column<String> = text("body")
    val favoritesCount: Column<Int> = integer("favorites_count").default(0)
    val createdAt: Column<LocalDateTime> = datetime("created_at")
    val updatedAt: Column<LocalDateTime> = datetime("updated_at")
}
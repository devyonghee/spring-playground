package me.devyonghee.flywayorm.article.persistence.exposed.table

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ArticleTable : IdTable<String>("article") {

    val slug: Column<String> = varchar("slug", 255)
    val title: Column<String> = varchar("title", 255)
    val description: Column<String> = varchar("name", 255)
    val body: Column<String> = text("body")
    val createdAt: Column<LocalDateTime> = datetime("createdAt")
    val updatedAt: Column<LocalDateTime> = datetime("updatedAt")

    override val id: Column<EntityID<String>>
        get() = slug.entityId()
}
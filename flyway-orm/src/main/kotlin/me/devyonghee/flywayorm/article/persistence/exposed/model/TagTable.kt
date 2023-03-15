package me.devyonghee.flywayorm.article.persistence.exposed.model

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

object TagTable : UUIDTable("tag") {

    val article: Column<EntityID<String>> = reference("article_slug", ArticleTable)
    val name: Column<String> = varchar("name", 255)

}
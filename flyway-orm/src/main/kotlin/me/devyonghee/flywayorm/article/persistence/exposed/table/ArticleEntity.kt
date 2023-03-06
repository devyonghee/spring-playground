package me.devyonghee.flywayorm.article.persistence.exposed.table

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ArticleEntity(slug: EntityID<String>) : Entity<String>(slug) {
    companion object : EntityClass<String, ArticleEntity>(ArticleTable)

    var slug by ArticleTable.slug
    var title by ArticleTable.title
    var description by ArticleTable.description
    var body by ArticleTable.body
    var createdAt by ArticleTable.createdAt
    var updatedAt by ArticleTable.updatedAt
    val tags by TagEntity referrersOn TagTable.article
}
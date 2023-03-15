package me.devyonghee.flywayorm.article.persistence.exposed.model

import me.devyonghee.flywayorm.article.domain.Article
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ArticleEntity(slug: EntityID<String>) : Entity<String>(slug) {
    companion object : EntityClass<String, ArticleEntity>(ArticleTable)

    var slug by ArticleTable.id
    var title by ArticleTable.title
    var description by ArticleTable.description
    var body by ArticleTable.body
    var favoritesCount by ArticleTable.favoritesCount
    var createdAt by ArticleTable.createdAt
    var updatedAt by ArticleTable.updatedAt
    val tags by TagEntity referrersOn TagTable.article

    fun toDomain(): Article {
        return Article(
            title,
            description,
            body,
            tags.map { it.toDomain() },
            slug.value,
            favoritesCount,
            createdAt,
            updatedAt,
        )
    }
}
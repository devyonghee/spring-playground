package me.devyonghee.flywayorm.article.persistence.exposed

import me.devyonghee.flywayorm.article.domain.Article
import me.devyonghee.flywayorm.article.persistence.exposed.model.ArticleEntity
import me.devyonghee.flywayorm.article.persistence.exposed.model.ArticleTable
import me.devyonghee.flywayorm.article.persistence.exposed.model.TagEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class ExposedArticleRepository {

    fun save(targetArticle: Article): String {
        return transaction {
            val newArticle = ArticleEntity.new(targetArticle.slug) {
                title = targetArticle.title
                description = targetArticle.description
                body = targetArticle.body
                createdAt = targetArticle.createdAt
                updatedAt = targetArticle.updatedAt
            }
            targetArticle.tags.forEach { tag ->
                TagEntity.new {
                    name = tag.name
                    article = newArticle
                }
            }
            newArticle
        }.id.value
    }

    fun findBySlug(slug: String): Article? {
        return transaction {
            ArticleEntity.find { ArticleTable.id eq slug }
                .firstOrNull()?.toDomain()
        }
    }
}
package me.devyonghee.flywayorm.article.persistence.exposed

import me.devyonghee.flywayorm.article.domain.Article
import me.devyonghee.flywayorm.article.persistence.exposed.table.ArticleEntity
import me.devyonghee.flywayorm.article.persistence.exposed.table.TagEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class ExposedArticleRepository {

    fun save(targetArticle: Article): String {
        return transaction {
            val newArticle = ArticleEntity.new {
                slug = targetArticle.slug
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
        }.slug
    }
}
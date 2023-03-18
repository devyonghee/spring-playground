package me.devyonghee.flywayorm.article.persistence

import me.devyonghee.flywayorm.article.domain.Article
import me.devyonghee.flywayorm.article.domain.ArticleRepository
import me.devyonghee.flywayorm.article.persistence.exposed.ExposedArticleRepository
import me.devyonghee.flywayorm.article.persistence.jooq.JooqArticleRepository
import me.devyonghee.flywayorm.article.persistence.jpa.JpaArticleRepository
import me.devyonghee.flywayorm.article.persistence.jpa.model.ArticleEntity
import me.devyonghee.flywayorm.article.persistence.querydsl.QuerydslArticleRepository
import org.springframework.stereotype.Repository

@Repository
class ArticleDao(
    private val jooqArticleRepository: JooqArticleRepository,
    private val exposedArticleRepository: ExposedArticleRepository,
    private val jpaArticleRepository: JpaArticleRepository,
    private val querydslArticleRepository: QuerydslArticleRepository,
) : ArticleRepository {

    override fun save(article: Article): String {
        return jpaArticleRepository.save(ArticleEntity(article)).slug
    }

    override fun findById(slug: String): Article? {
        return querydslArticleRepository.findBySlug(slug)
    }
}
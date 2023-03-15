package me.devyonghee.flywayorm.article.persistence

import me.devyonghee.flywayorm.article.domain.Article
import me.devyonghee.flywayorm.article.domain.ArticleRepository
import me.devyonghee.flywayorm.article.persistence.exposed.ExposedArticleRepository
import me.devyonghee.flywayorm.article.persistence.jooq.JooqArticleRepository
import org.springframework.stereotype.Repository

@Repository
class ArticleDao(
    private val jooqArticleRepository: JooqArticleRepository,
    private val exposedArticleRepository: ExposedArticleRepository,
) : ArticleRepository {

    override fun save(article: Article): String {
        return exposedArticleRepository.save(article)
    }

    override fun findById(slug: String): Article? {
        return exposedArticleRepository.findBySlug(slug)
    }
}
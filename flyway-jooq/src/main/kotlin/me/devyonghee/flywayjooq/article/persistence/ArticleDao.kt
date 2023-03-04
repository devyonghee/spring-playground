package me.devyonghee.flywayjooq.article.persistence

import me.devyonghee.flywayjooq.article.domain.Article
import me.devyonghee.flywayjooq.article.domain.ArticleRepository
import me.devyonghee.flywayjooq.article.persistence.jooq.JooqArticleRepository
import org.springframework.stereotype.Repository

@Repository
class ArticleDao(
    private val jooqArticleRepository: JooqArticleRepository
) : ArticleRepository {

    override fun save(article: Article): String {
        return jooqArticleRepository.save(article)
    }

    override fun findById(slug: String): Article? {
        return jooqArticleRepository.findBySlug(slug)
    }
}
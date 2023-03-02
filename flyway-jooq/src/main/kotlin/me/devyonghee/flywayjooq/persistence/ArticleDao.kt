package me.devyonghee.flywayjooq.persistence

import me.devyonghee.flywayjooq.domain.Article
import me.devyonghee.flywayjooq.domain.ArticleRepository
import me.devyonghee.flywayjooq.persistence.jooq.JooqArticleRepository
import org.springframework.stereotype.Repository

@Repository
class ArticleDao(
    private val jooqArticleRepository: JooqArticleRepository
) : ArticleRepository {

    override fun save(article: Article): String {
        return jooqArticleRepository.save(article)
    }

    override fun findById(slug: String): Article? {
        return jooqArticleRepository.findById(slug)
    }
}
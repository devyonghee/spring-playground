package me.devyonghee.kotlinjooq.persistence

import me.devyonghee.kotlinjooq.domain.Article
import me.devyonghee.kotlinjooq.domain.ArticleRepository
import me.devyonghee.kotlinjooq.persistence.jooq.JooqArticleRepository
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
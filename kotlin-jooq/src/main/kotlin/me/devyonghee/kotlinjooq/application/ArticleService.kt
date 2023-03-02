package me.devyonghee.kotlinjooq.application

import me.devyonghee.kotlinjooq.domain.Article
import me.devyonghee.kotlinjooq.domain.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) {
    fun save(article: Article):String {
        return articleRepository.save(article)
    }

    fun findOne(slug: String): Article {
        return articleRepository.findById(slug)
            ?: throw NoSuchElementException("article(slug: '$slug') not exists")
    }
}

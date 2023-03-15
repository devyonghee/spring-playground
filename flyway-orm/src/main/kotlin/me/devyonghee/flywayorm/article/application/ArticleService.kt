package me.devyonghee.flywayorm.article.application

import me.devyonghee.flywayorm.article.domain.Article
import me.devyonghee.flywayorm.article.domain.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) {

    @Transactional
    fun save(article: Article): String {
        return articleRepository.save(article)
    }

    @Transactional(readOnly = true)
    fun findOne(slug: String): Article {
        return articleRepository.findById(slug)
            ?: throw NoSuchElementException("article(slug: '$slug') not exists")
    }
}

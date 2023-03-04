package me.devyonghee.flywayjooq.article.domain

interface ArticleRepository {

    fun save(article: Article): String

    fun findById(slug: String): Article?
}

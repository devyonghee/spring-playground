package me.devyonghee.flywayjooq.domain

interface ArticleRepository {

    fun save(article: Article): String

    fun findById(slug: String): Article?
}

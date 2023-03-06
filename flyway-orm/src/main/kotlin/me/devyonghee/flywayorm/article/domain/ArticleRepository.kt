package me.devyonghee.flywayorm.article.domain

interface ArticleRepository {

    fun save(article: Article): String

    fun findById(slug: String): Article?
}

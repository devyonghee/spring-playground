package me.devyonghee.flywayorm.article.controller.response

import me.devyonghee.flywayorm.article.domain.Article
import me.devyonghee.flywayorm.article.domain.Tag
import java.time.LocalDateTime

data class ArticleResponse(
    private val article: Article
) {
    val title: String = article.title
    val description: String = article.description
    val body: String = article.body
    val tags: List<String> = article.tags.names
    val createdAt: LocalDateTime = article.createdAt
    val updatedAt: LocalDateTime = article.updatedAt
    val favoritesCount: Int = article.favoritesCount
}

val List<Tag>.names: List<String>
    get() = this.map { it.name }

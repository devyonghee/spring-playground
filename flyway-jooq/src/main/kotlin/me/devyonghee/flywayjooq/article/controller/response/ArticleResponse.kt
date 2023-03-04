package me.devyonghee.flywayjooq.article.controller.response

import me.devyonghee.flywayjooq.article.domain.Article
import me.devyonghee.flywayjooq.article.domain.Tag
import java.time.LocalDateTime

data class ArticleResponse(
    private val article: Article
) {
    val title: String = article.title
    val description: String = article.description
    val body: String = article.body
    val tags: List<String> = article.tags.strings
    val createdAt: LocalDateTime = article.createdAt
    val updatedAt: LocalDateTime = article.updatedAt
    val favorited: Boolean = article.favorited
    val favoritesCount: Int = article.favoritesCount
}

val List<Tag>.strings: List<String>
    get() = this.map { it.name }

package me.devyonghee.flywayjooq.article.persistence.jooq.dto

import me.devyonghee.flywayjooq.article.domain.Article

data class ArticleDto(
    val slug: String,
    val title: String,
    val description: String,
    val body: String,
    val tags: List<TagDto>,
) {
    fun toDomain(): Article = Article(title, description, body, tags.toDomain(), slug)
}
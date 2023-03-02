package me.devyonghee.flywayjooq.persistence.jooq.dto

import me.devyonghee.flywayjooq.domain.Article

data class ArticleDto(
    val slug: String,
    val title: String,
    val description: String,
    val body: String,
    val tags: List<TagDto>,
) {
    val domain: Article
        get() = Article(title, description, body, tags.domain, slug)
}
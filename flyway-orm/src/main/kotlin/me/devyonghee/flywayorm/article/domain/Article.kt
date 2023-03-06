package me.devyonghee.flywayorm.article.domain

import java.time.LocalDateTime
import java.util.*

class Article(
    val title: String,
    val description: String,
    val body: String,
    val tags: List<Tag>,
    val slug: String = title.lowercase(Locale.getDefault())
        .replace(" ", "-"),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val favorited: Boolean = false,
    val favoritesCount: Int = 0,
) {
    init {
        require(title.isNotBlank()) { "title must not be blank" }
    }
}

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
    val favoritesCount: Int = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    init {
        require(title.isNotBlank()) { "title must not be blank" }
    }
}
